package dungeonmania.map;
import dungeonmania.util.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import java.util.Iterator;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Ring;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.Interactable;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.MovableNPC;
import dungeonmania.entity.square.Boulder;
import dungeonmania.entity.square.Door;
import dungeonmania.entity.square.Portal;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.*;

import java.util.List;

public class DungeonMap implements DungeonMapAPI {

    private Map<Position,List<EntityAPI>> entities;
    private String goals;
    private Player player;
    private ArrayList<Enemy> battlingNPCs;
    private ArrayList<Enemy> allies;
    
    public DungeonMap() {
        entities = new Hashtable<>();
        battlingNPCs = new ArrayList<>();
        allies = new ArrayList<>();
    }

    public ArrayList<EntityAPI> getAllEntitiesInMap(){
        ArrayList<EntityAPI> retList = new ArrayList<>();
        for (Position position : entities.keySet()){
            retList.addAll(entities.get(position));
        }
        return retList;
    }  

    public static <E> List<E> shallowClone(List<E> list){
        List<E> retList = new ArrayList<>();
        for (E entity : list){
            retList.add(entity);
        }
        return retList;
    }

    public boolean playerCanMoveToward(Direction direction){
        Position newPos = player.getPosition().translateBy(direction);
        if (entities.get(newPos) == null)
            return true;

        for (EntityAPI entity : entities.get(newPos)){
            boolean allowToMove = true;
            if (entity instanceof Boulder)
                allowToMove = ((Boulder)entity).canMove(direction);
            else if (entity instanceof Door)
                allowToMove = ((Door)entity).canUnlock(player.getNonBattleItems());
            else
                allowToMove = entity.canBeOnSamePosition(player);
            
            if (!allowToMove)
                return false;
        }
        return true;
    }

    public void playerMove(Direction movementDirection){
        if (playerCanMoveToward(movementDirection)) {
            Position newPos = player.getPosition().translateBy(movementDirection);
            if (entities.get(newPos)!= null){
                for (EntityAPI entity : shallowClone(entities.get(newPos))){
                    if (entity instanceof Boulder){
                        doLeaveAction((Boulder)entity);
                        moveFromPositionTo(entity, entity.getPosition().translateBy(movementDirection));
                        doCollideAction((Boulder)entity);
                    }
                    else if (entity instanceof Door){
                        ((Door)entity).unlock(player.getNonBattleItems());
                    }
                    else if (entity instanceof Portal){
                        newPos = ((Portal)entity).teleportLocation();
                    }
                }
            }
            doLeaveAction(player);
            moveFromPositionTo(player, newPos);
        } 

    }

    public boolean canBeInPosition(Boulder boulder, Position newPos){
        if (entities.get(newPos) == null)
            return true;
        for (EntityAPI arrayEntity: entities.get(newPos)){
            if (!arrayEntity.canBeOnSamePosition(boulder)){
                return false;
            }
        }
        return true;
    }

    public boolean canBeInPosition(Enemy enemy, Position newPos){
        if (!entities.keySet().contains(newPos))
            return true;
        for (EntityAPI arrayEntity: entities.get(newPos)){
            if (!arrayEntity.canBeOnSamePosition(enemy))
                return false;
        }
        return true;
    }

    public void moveFromPositionTo(EntityAPI entity, Position to){
        entities.get(entity.getPosition()).remove(entity);
        entity.setPosition(to);
        addEntity(entity);
    }

    public void tick(String itemUsedId, Direction movementDirection) throws IllegalArgumentException, InvalidActionException{
        if (itemUsedId != null){
            player.use(itemUsedId);
        }
        else{
            playerMove(movementDirection);
        }
        doCollideAction(player);
        player.updatePotionEffects();
        

        //resolve battle numbers, reward and stuff
        while (!battlingNPCs.isEmpty()){
            roundBattle();
        }
        //TODO GOAL
        //goal.isSatisfied();
    }

    public void interact(String entityId) throws IllegalArgumentException, InvalidActionException{
        for (EntityAPI entity : getAllEntitiesInMap()){
            if (entity.isInteractable() && entityId.equals(entity.getId())){
                ((Interactable)entity).interact();
                return;
            }
        }
        throw new IllegalArgumentException("entityId is not a valid entity ID");
    }

    // public <T extends Entity> void doCollideAction(T entity){
    //     if (entities.get(entity.getPosition()) != null){
    //         for (EntityAPI arrayEntity : shallowClone(entities.get(entity.getPosition()))){
    //             if (!battlingNPCs.contains(arrayEntity))
    //                 arrayEntity.collideAction(entity);
    //         }
    //     }
    // }  
    
    public void doCollideAction(Boulder boulder){
        if (entities.get(boulder.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(boulder.getPosition()))){
                if (!battlingNPCs.contains(entity))
                    entity.collideAction(boulder);
            }
        }
    }  

    public void doCollideAction(Player player){
        if (entities.get(player.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(player.getPosition()))){
                if (!battlingNPCs.contains(entity))
                    entity.collideAction(player);
            }
        }
    }  

    public void doLeaveAction(Boulder boulder){
        if (entities.get(boulder.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(boulder.getPosition()))){
                if (!battlingNPCs.contains(entity))
                    entity.leaveAction(boulder);
            }
        }
    }  

    public void doLeaveAction(Player player){
        if (entities.get(player.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(player.getPosition()))){
                if (!battlingNPCs.contains(entity))
                    entity.leaveAction(player);
            }
        }
    }  

    public void addToBattle(Enemy enemy){
        battlingNPCs.add(enemy);
    }

    public void roundBattle(){
        ArrayList<Enemy> defeatedEnemies = new ArrayList<>();
        ArrayList<Enemy> retreatedEnemies = new ArrayList<>();
        BattleStat playerStat = player.getBattleStat();
        //now only allows character to battle, no allies
        for (Enemy enemy : battlingNPCs){
            BattleStat enemyStat = enemy.getBattleStat();
            if (player.isInvincible()){
                defeatedEnemies.add(enemy);
            }
            // else if(player.isInvisible()){
            //     retreatedEnemies.add(enemy);
            // }
            else{
                // resolve numbers for battling
                int playerReceivedDamage = (playerStat.getReducedAttack(enemyStat.getAttack()) * enemyStat.getHealth()) / 10;
                int enemyReceivedDamage = (enemyStat.getReducedAttack(playerStat.getAttack()) * playerStat.getHealth()) / 5;
                playerStat.reduceHealth(playerReceivedDamage);
                enemyStat.reduceHealth(enemyReceivedDamage);
                playerStat.reduceAllDurability();
                playerStat.removeAllDeteriorated();
                enemyStat.reduceAllDurability();
                enemyStat.removeAllDeteriorated();
                if (playerStat.getHealth() <= 0){
                    //TODO player loses and game ends
                    
                }
                else if(enemyStat.getHealth() <= 0){
                    defeatedEnemies.add(enemy);
                }
            }
        }
        playerDefeatsEnemies(defeatedEnemies);
    }

    public void enemiesRetreats(ArrayList<Enemy> enemies){
        for (Enemy enemy : enemies){
            battlingNPCs.remove(enemy); 
        }       
    }

    private void playerDefeatsEnemies(ArrayList<Enemy> enemies){
        for (Enemy enemy : enemies){
            BattleStat enemyStat = enemy.getBattleStat();
            for (BattleGear battleGear : enemyStat.getBattleGears()){
                player.addCollectable((Collectable)battleGear);
            }
            boolean wonOneRing = (new Random().nextInt(1)==0);
            if (wonOneRing){
                player.addCollectable(new Ring("one_ring", this, player));
            }
            removeEntity(enemy);
        }
    }

    public void addEntity(EntityAPI newEntity) {
        if (!entities.containsKey(newEntity.getPosition())) {
            entities.put(newEntity.getPosition(), new ArrayList<EntityAPI>());
        }
            entities.get(newEntity.getPosition()).add(newEntity);
    }

    public void removeEntity(EntityAPI entity){
        entities.get(entity.getPosition()).remove(entity);
        battlingNPCs.remove(entity);
    }

    public ArrayList<Enemy> getAllies(){
        return allies;
    }

    @Override
    public List<EntityResponse> toEntityResponseList() {
        List<EntityResponse> info = new ArrayList<>();
        entities.entrySet().stream().map(e -> e.getValue()).forEach(e->e.stream().forEach(k -> info.add(k.toEntityResponse())));
        return info;
    }

    
    @Override
    public List<ItemResponse> toItemResponseList() {
        return player.inventoryToItemResponseList();
    }

    public String getGoals() {
        return goals;
    }
    public void setGoals(String goals) {
        this.goals = goals;
    }

    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }

    public Player getPlayer() {
        return player;
    }

}

package dungeonmania.map;
import dungeonmania.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import java.util.Iterator;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Effect;
import dungeonmania.entity.collectable.Ring;
import dungeonmania.entity.creature.Creature;
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
    private ArrayList<Enemy> enemyFaction;
    private ArrayList<Creature> allies;

    private ArrayList<Effect> effectsInAction;
    
    public DungeonMap() {
        entities = new Hashtable<>();
        enemyFaction = new ArrayList<>();
        allies = new ArrayList<>();
        effectsInAction = new ArrayList<>();
    }

    public ArrayList<EntityAPI> getAllEntitiesInMap(){
        ArrayList<EntityAPI> retList = new ArrayList<>();
        for (Position position : entities.keySet()){
            retList.addAll(entities.get(position));
        }
        return retList;
    }  

    public static <E> ArrayList<E> shallowClone(List<E> list){
        ArrayList<E> retList = new ArrayList<>();
        for (E entity : list){
            retList.add(entity);
        }
        return retList;
    }

    public void addEffectInAction(Effect effect){
        effectsInAction.add(effect);
    }

    public void removeEffectInAction(Effect effect){
        effectsInAction.remove(effect);
    }

    private void updateEffects(){
        for (Effect effect : DungeonMap.shallowClone(effectsInAction)){
            effect.updateEffectDuration();    
        }
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
        updateEffects();
        

        //resolve battle numbers, reward and stuff
        if (!enemyFaction.isEmpty())
            battle();
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
    //             arrayEntity.collideAction(entity);
    //         }
    //     }
    // }  
    
    public void doCollideAction(Boulder boulder){
        if (entities.get(boulder.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(boulder.getPosition()))){
                entity.collideAction(boulder);
            }
        }
    }  

    public void doCollideAction(Player player){
        if (entities.get(player.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(player.getPosition()))){
                entity.collideAction(player);
            }
        }
    }  

    public void doLeaveAction(Boulder boulder){
        if (entities.get(boulder.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(boulder.getPosition()))){
                entity.leaveAction(boulder);
            }
        }
    }  

    public void doLeaveAction(Player player){
        if (entities.get(player.getPosition()) != null){
            for (EntityAPI entity : shallowClone(entities.get(player.getPosition()))){
                entity.leaveAction(player);
            }
        }
    }  

    public void addToBattle(Enemy enemy){
        enemyFaction.add(enemy);
    }

    public void addToAlly(Creature ally){
        allies.add(ally);
    }

    public void removeFromAlly(Creature enemy){
        allies.remove(enemy);
    }

    public void battle(){
        ArrayList<Enemy> defeatedEnemies = new ArrayList<>();
        ArrayList<Creature> defeatedAllies = new ArrayList<>();

        if (player.isInvincible()){
            for (Enemy enemy : enemyFaction)
                defeatedEnemies.add(enemy);
            playerDefeatsEnemies(defeatedEnemies);
            return;
        }
        
        ArrayList<Creature> playerFaction = new ArrayList<>();
        playerFaction.add(player);
        playerFaction.addAll(allies);

        while (defeatedEnemies.size() != enemyFaction.size()){
            for (int j = 0; j < enemyFaction.size() && !defeatedEnemies.contains(enemyFaction.get(j)); j++){
                Enemy enemy = enemyFaction.get(j);
                BattleStat enemyStat = enemy.getBattleStat();
                for (int i = 0; i < playerFaction.size() && !defeatedAllies.contains(playerFaction.get(i)) && !defeatedEnemies.contains(enemy); i++){
                    Creature ally = playerFaction.get(i);
                    BattleStat allyStat = ally.getBattleStat();
                    System.out.println("-----------------------------------------------------");
                    displayBattleInfo(ally, enemy);
                    double allyReceivedDamage = (allyStat.getReducedAttack(enemyStat.getAttack()) * enemyStat.getHealth()) / 10;
                    double enemyReceivedDamage = (enemyStat.getReducedAttack(allyStat.getAttack()) * allyStat.getHealth()) / 5;
                    allyStat.reduceHealth(allyReceivedDamage, enemy);
                    enemyStat.reduceHealth(enemyReceivedDamage, ally);
                    allyStat.reduceAllDurability();
                    allyStat.removeAllDeteriorated();
                    enemyStat.reduceAllDurability();
                    enemyStat.removeAllDeteriorated();
                    if (allyStat.getHealth() <= 0){
                        defeatedAllies.add(ally);
                    }
                    if(enemyStat.getHealth() <= 0){
                        defeatedEnemies.add(enemy);
                    }
                    displayBattleInfo(ally, enemy);
                   System.out.println("defeatedEnemies" + defeatedEnemies);
                   System.out.println("defeatedAllies" + defeatedAllies);
                   System.out.println("-----------------------------------------------------");
                    if(defeatedAllies.contains(player)){
                        removeEntity(player);
                        return;
                    }
                }
            }
        }

        System.out.println("BattleEnded");

        //playerFaction won
        playerDefeatsEnemies(defeatedEnemies);

        for (Creature ally : defeatedAllies){
            removeEntity(ally);
        }

    }

    public void displayBattleInfo(Creature ally, Enemy enemy){
        BattleStat allyBattleStat = ally.getBattleStat();
        BattleStat enemyBattleStat = enemy.getBattleStat();
        System.out.println(ally.getType() + "vs" + enemy.getType());
        System.out.println(String.format("ally  health: %f  attack: %f", allyBattleStat.getHealth(), allyBattleStat.getAttack()));
        System.out.println(String.format("enemy  health: %f  attack: %f", enemyBattleStat.getHealth(), enemyBattleStat.getAttack()));
    }

    public void playerDefeatsEnemies(ArrayList<Enemy> defeatedEnemies){
        for (Enemy enemy : defeatedEnemies){
            BattleStat enemyStat = enemy.getBattleStat();
            for (BattleGear battleGear : enemyStat.getBattleGears()){
                player.addCollectable((Collectable)battleGear);
            }
            removeEntity(enemy);
        }
        boolean wonOneRing = (new Random().nextInt(1)==0);
        if (wonOneRing){
            player.addCollectable(new Ring("one_ring", this, player));
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
        enemyFaction.remove(entity);
        allies.remove(entity);
    }

    public ArrayList<Creature> getAllies(){
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

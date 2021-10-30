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
import dungeonmania.entity.interfaces.Guard;
import dungeonmania.entity.interfaces.RegularActionEntity;
import dungeonmania.entity.interfaces.Weapon;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.*;

import java.util.List;

public class DungeonMap implements DungeonMapAPI {

    private Map<Position,List<EntityAPI>> entities;
    private String goals;
    private Player player;
    private ArrayList<Enemy> battlingNPCs;
    //includes entity in inventories
    //private ArrayList<Entity> allEntities;
    
    public DungeonMap() {
        entities = new Hashtable<>();
        //inventory = new ArrayList<>();
    }
    // private ArrayList<CollideActionEntity> collideActionEntities;
    // private ArrayList<RegularActionEntity> regularActionEntities;
    //private ArrayList<Entity> allEntities;

    public ArrayList<EntityAPI> getAllEntityAPIs(){
        ArrayList<EntityAPI> ret = new ArrayList<>();
        for (List<EntityAPI> list : entities.values()){
            ret.addAll(list);
       }

       return ret;
    }

   /* public DungeonResponse tick(String itemUsedId, Direction movementDirection) throws IllegalArgumentException, InvalidActionException{

        //all entitys preform player non dependent actions like moving and spawning
        for (EntityAPI entity : getAllEntityAPIs()){
            if (entity.isDynamic())
                ((RegularActionEntity)entity).regularAction();
        }

        //player use items, may throw exception 
        player.use(itemUsedId);
        player.updatePotionEffects();


        //TODO player moves here

        //player moves here end

        //entity on the same cell as player perform action like initiating battle and items being picked up
        for (EntityAPI entity : entities.get(player.getPosition())){
            entity.collideAction(player, );
        }

        //resolve battle numbers, reward and stuff
        resolveBattle();

        //TODO GOAL
        //goal.isSatisfied();

        //return the dungeonresponse object
        return null;
    }*/

    public void removeEntityFromMap(EntityAPI entity){
        List<EntityAPI> checkList = entities.get(entity.getPosition());
        checkList.remove(entity);
        //battlingNPCs.remove(entity);
    }

    public void addToBattle(Enemy enemy){
        battlingNPCs.add(enemy);
    }

    public void retreatFromBattle(Enemy enemy){
        battlingNPCs.remove(enemy);        
    }

    public void resolveBattle(){
        BattleStat playerStat = player.getBattleStat();
        //now only allows character to battle, no allies
        for (Enemy enemy : battlingNPCs){
            BattleStat enemyStat = enemy.getBattleStat();
            if (player.isInvincible()){
                playerDefeatsEnemy(enemy);
            }
            else if(player.isInvisible()){
                retreatFromBattle(enemy);
            }
            else{
                // resolve numbers for battling
                int playerReceivedDamage = (playerStat.getReducedAttack(enemyStat.getAttack()) * enemyStat.getHealth()) / 10;
                int enemyReceivedDamage = (enemyStat.getReducedAttack(playerStat.getAttack()) * playerStat.getHealth()) / 5;
                playerStat.reduceHealth(playerReceivedDamage);
                enemyStat.reduceHealth(enemyReceivedDamage);
                if (playerStat.getHealth() <= 0){
                    //TODO player loses and game ends
                    
                }
                else if(enemyStat.getHealth() <= 0){
                    playerDefeatsEnemy(enemy);
                }
            }
        }
    }

    private void playerDefeatsEnemy(Enemy enemy){
        BattleStat enemyStat = enemy.getBattleStat();

        for (Weapon weapon : enemyStat.getWeapons()){
            player.addCollectable((Collectable)weapon);
        }
        for (Guard guard : enemyStat.getGuards()){
            player.addCollectable((Collectable)guard);
        }
        boolean wonOneRing = (new Random().nextInt(20)==0);
        if (wonOneRing){
            player.addCollectable(new Ring("one_ring", this, player));
        }
              
        removeEntityFromMap(enemy);
    }

    public void addEntity(EntityAPI newEntity) {
        if (!entities.containsKey(newEntity.getPosition())) {
            entities.put(newEntity.getPosition(), new ArrayList<EntityAPI>());
        }
            List<EntityAPI> addNew = entities.get(newEntity.getPosition());
            addNew.add(newEntity);
    }

    public List<EntityResponse> getInfoList() {
        List<EntityResponse> info = new ArrayList<>();
        entities.entrySet().stream().map(e -> e.getValue()).forEach(e->e.stream().forEach(k -> info.add(k.getInfo())));
        return info;
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

    public boolean checkLocation(Position check) {
        if (entities.containsKey(check))
            return true;
        return false;
    }
    
    public void collideAction(Player player, Position currentPosition) {

        Iterator <EntityAPI> checkList = entities.get(currentPosition).iterator();
        EntityAPI current;
        while (checkList.hasNext()) {
            current = checkList.next();
            if (current instanceof Collectable) {
                current.action(player, currentPosition);
                checkList.remove();  
            } else {
                current.action(player, currentPosition); 
            }
        }
    }

        
        
        /*boolean stop = false;
        while(!stop) {
            stop = true;
            for (EntityAPI diffEntities: checkList) {
                //we will need to talk about how we avoid this concurrent modification problem
                if (diffEntities instanceof Collectable) {
                    stop = false;
                    diffEntities.action(player, currentPosition);
                    break;

                }
                diffEntities.action(player, currentPosition);
            }  
        }*/              

    public List<ItemResponse> getItemInfoList() {
        return player.inventoryToItemResponse();
    }
}

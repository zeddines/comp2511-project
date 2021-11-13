package dungeonmania.map;
import dungeonmania.goal.*;
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
    private AllGoals allGoals = new AllGoals();
    private Player player;
    private ArrayList<Enemy> battlingNPCs;
    //includes entity in inventories
    //private ArrayList<Entity> allEntities;
    
    public DungeonMap() {
        entities = new Hashtable<>();
        battlingNPCs = new ArrayList<>();
    }

    public ArrayList<EntityAPI> getAllEntityAPIs(){
        ArrayList<EntityAPI> ret = new ArrayList<>();
        for (List<EntityAPI> list : entities.values()){
            ret.addAll(list);
       }

       return ret;
    }

    private boolean canMoveToPosition(Position newPos){
        if (!entities.keySet().contains(newPos))
            return true;
        for (EntityAPI entity : entities.get(newPos)){
            if (!entity.canCoExist())
                return false;
        }
        return true;
    }
    public void tick(String itemUsedId, Direction movementDirection, DungeonResponse d) throws IllegalArgumentException, InvalidActionException{

        //all entitys preform player non dependent actions like moving and spawning
        // for (EntityAPI entity : getAllEntityAPIs()){
        //     if (entity.isDynamic())
        //         ((RegularActionEntity)entity).regularAction();
        // }

        //player use items, may throw exception
         
        if (itemUsedId != null){
            player.use(itemUsedId);
        }
        player.updatePotionEffects();
        
        //player moves
        Position checkPosition = player.getPosition();
        checkPosition = checkPosition.translateBy(movementDirection);
        if (canMoveToPosition(checkPosition)) {
            player.setPosition(checkPosition);
        } 
        //player moves end
        if (entities.get(player.getPosition()) != null && !(entities.get(player.getPosition()).isEmpty())){
            Iterator <EntityAPI> entityIterator = entities.get(player.getPosition()).iterator();
            EntityAPI current;
            while (entityIterator.hasNext()) {
                current = entityIterator.next();
                if (!battlingNPCs.contains(current)){
                    if (current instanceof Collectable) {
                        current.action(player);
                        entityIterator.remove();  
                    } else {
                        current.action(player); 
                    }
                }
            }
        }
        //resolve battle numbers, reward and stuff
        while (!battlingNPCs.isEmpty()){
            roundBattle();
        }

        //TODO GOAL
        goals = allGoals.goalSatisfied(d);

        //return the dungeonresponse object
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
            else if(player.isInvisible()){
                retreatedEnemies.add(enemy);
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
                
            entities.get(enemy.getPosition()).remove(enemy);
            battlingNPCs.remove(enemy);
        }
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
//        System.out.println("Hello");
        this.goals = goals;

//        System.out.println(goals);

        String[] parts;

        if (goals.contains("AND")) {
            parts = goals.split("AND");

            for (String s: parts) {
//                System.out.println(s);

                if (s.equals("boulders")) {
                    allGoals.addGoal(new BoulderGoal());
                } else if (s.equals("enemies")) {
                    allGoals.addGoal(new Enemies());
                } else if (s.equals("treasure")) {
                    allGoals.addGoal(new TreasureGoal());
                } else if (s.equals("exit")) {
                    allGoals.addGoal(new ExitGoal());
                }
            }

            allGoals.addGoal(new And());
        } else if (goals.contains("OR")) {
            parts = goals.split("OR");

            for (String s: parts) {
//                System.out.println(s);

                if (s.equals("boulders")) {
                    allGoals.addGoal(new BoulderGoal());
                } else if (s.equals("enemies")) {
                    allGoals.addGoal(new Enemies());
                } else if (s.equals("treasure")) {
                    allGoals.addGoal(new TreasureGoal());
                } else if (s.equals("exit")) {
                    allGoals.addGoal(new ExitGoal());
                }
            }

            allGoals.addGoal(new Or());
        } else {
            if (goals.equals("boulders")) {
                allGoals.addGoal(new BoulderGoal());
            } else if (goals.equals("enemies")) {
                allGoals.addGoal(new Enemies());
            } else if (goals.equals("treasure")) {
                allGoals.addGoal(new TreasureGoal());
            } else if (goals.equals("exit")) {
                allGoals.addGoal(new ExitGoal());
            }
        }

    }

    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public List<ItemResponse> getItemInfoList() {
        return player.inventoryToItemResponse();
    }
}

package dungeonmania.map;
import dungeonmania.goal.*;
import dungeonmania.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.eclipse.jetty.servlet.ServletHandler.Default404Servlet;

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
import dungeonmania.entity.square.Spawner;
import dungeonmania.entityfactory.BuildableFactory;
import dungeonmania.entityfactory.FactoryFront;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.*;

import java.util.List;

public class DungeonMap implements DungeonMapAPI {
    private FactoryFront factory;

    private Map<Position,List<EntityAPI>> entities;
    private String goals;
    private AllGoals allGoals = new AllGoals();
    private Player player;
    private ArrayList<Enemy> enemyFaction;
    private ArrayList<Creature> allies;
    
    private Position entryPosition;
    private int tick;
    private ArrayList<Effect> effectsInAction;
    
    public DungeonMap() {
        entities = new Hashtable<>();
        enemyFaction = new ArrayList<>();
        allies = new ArrayList<>();
        effectsInAction = new ArrayList<>();
        tick = 0;
    }

    public void setFactory(FactoryFront factory){
        this.factory = factory; 
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

    public void spawn(){
        int maxX = getMapSizeX();
        int maxY = getMapSizeY();
        Random rand = new Random();
        if (tick == 0){
            entryPosition = getPlayer().getPosition();
            int numOfSpiders = rand.nextInt(5);
            for (int i = 0; i < numOfSpiders; i++){
                Position tryPosition;
                Enemy enemy;
                do{
                tryPosition = new Position(rand.nextInt(maxX), rand.nextInt(maxY));
                enemy = factory.makeEnemy("spider", tryPosition);
                } while (!canBeInPosition(enemy, tryPosition));
                addEntity(enemy);
            }
        }
        else if (tick%20 == 0){
            if (rand.nextInt(5) == 0)
                addEntity(factory.makeEnemy("assassin", entryPosition));
            else if (rand.nextInt(3) == 0)
                addEntity(factory.makeEnemy("mercenary", entryPosition, new ArrayList<>(Arrays.asList("armour"))));
            else
                addEntity(factory.makeEnemy("mercenary", entryPosition));
        }
        for (EntityAPI entity : getAllEntitiesInMap()){
            if (entity instanceof Spawner)
                ((Spawner)entity).spawn();
        }
    }

    public void tick(String itemUsedId, Direction movementDirection, DungeonResponse d) throws IllegalArgumentException, InvalidActionException{
        spawn();
        if (itemUsedId != null){
            player.use(itemUsedId);
        }
        else{
            playerMove(movementDirection);
        }

        //NPC MOVEMENT, TODO INCORPORATE THE LOGIC OF SWAMP
        //MY SUGGESTION IS MAKE A HASHMAP WHICH STORES WHICH NPC
        //AND NOW LONG THEY ARE STUCK, THEN SIMPLY DON'T CALL THEM TO 
        //MOVE() WHEN THEY ARE STUCK
        //UPDATE THE HASHMAP EVERY TICK
        for (EntityAPI entity : getAllEntitiesInMap()){
            if (entity instanceof Enemy)
                ((Enemy)entity).move();
        }
        //NPC MOVEMENT ENDS HERE//
        
        doCollideAction(player);
        updateEffects();

        //resolve battle numbers, reward and stuff
        if (!enemyFaction.isEmpty())
            battle();
        tick++;

        //TODO GOAL
        goals = allGoals.goalSatisfied(d);
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

    public void build(String buildable) throws IllegalArgumentException, InvalidActionException{
        if (!Arrays.asList(BuildableFactory.buildables).contains(buildable)){
            throw new IllegalArgumentException("is not a buildable entity");
        }

        Collectable newItem = factory.makeBuildable(buildable);
        if (newItem == null)
            throw new InvalidActionException("player does not have sufficient items to craft the buildable");
        else
            player.addCollectable(newItem);
    }
        

    public List<String> buildableItems(){
        return factory.getAllBuildableItems(player.getAllCollectables());
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
            for (int j = 0; j < enemyFaction.size(); j++){
                if (!defeatedEnemies.contains(enemyFaction.get(j))){
                    Enemy enemy = enemyFaction.get(j);
                    BattleStat enemyStat = enemy.getBattleStat();
                    for (int i = 0; i < playerFaction.size() && !defeatedEnemies.contains(enemy); i++){
                        if (!defeatedAllies.contains(playerFaction.get(i))){
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
                            System.out.println("allEnimies" + enemyFaction);
                            System.out.println("enemysize" + enemyFaction.size());
                            System.out.println("allAllies" + playerFaction);
                            System.out.println("alliesSize" + playerFaction.size());
                            System.out.println("enemysize" + enemyFaction.size());
                            System.out.println("defeatedEnemies" + defeatedEnemies);
                            System.out.println("defeatedEnemySize" + defeatedEnemies.size());
                            System.out.println("defeatedAllies" + defeatedAllies);
                            System.out.println("-----------------------------------------------------");
                            if(defeatedAllies.contains(player)){
                                removeEntity(player);
                                return;
                            }
                        }
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
        boolean wonOneRing = (new Random().nextInt(10)==0);
        if (wonOneRing){
            player.addCollectable(factory.makeCollectable("one_ring", player));
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

    public FactoryFront getFactory(){
        return factory;
    }

    public int getMapSizeX(){
        int x = 0;
        for (EntityAPI entity : getAllEntitiesInMap()){
            if (entity.getPosition().getX() > x){
                x = entity.getPosition().getX();
            }
        }
        return x + 1;
    }

    public int getMapSizeY(){
        int y = 0;
        for (EntityAPI entity : getAllEntitiesInMap()){
            if (entity.getPosition().getY() > y){
                y = entity.getPosition().getY();
            }
        }
        return y + 1;
    }

    /////TODO FOR GOWTHAM////

    //GETWEIGHTOFPOSITION

    //CREATE THE SWAMP CLASS AND ADD IT TO FACTORY

    //private boolean specialMovementApplied;

    //SWITCHALLTODEFAULT, set specialMovementApplied to false;

    //NOTE SWITCH FOR FLEE OR RANDOM SHOULD ONLY BE DONE WHEN NO OTHER SPECIAL MOVEMENT IS APPLYING
    //OR ELSE IT WILL CONFLICT AND RESULT IN UNEXPECTED BEHAVIOUR
    //SWITCHALLTOFLEE, set specialMovementApplied to true;


    //SWITCHALLTORANDOM, set specialMovementApplied to true;

    //CALL THESE SWITCH METHODS IN POTION EFFECT
}

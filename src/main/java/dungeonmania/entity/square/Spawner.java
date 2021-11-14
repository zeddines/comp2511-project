package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Armour;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.NonInteractableEnemy;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.Interactable;
import dungeonmania.entity.interfaces.MovableNPC;
import dungeonmania.entityfactory.FactoryFront;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

public class Spawner extends Entity implements Interactable{
    private Map<String, Integer> spawnCoolDowns;
    private Map<String, Integer> roundsLeftToSpawn;
    /**
     *  Spawns zombie toasts every 20 ticks in an open square cardinally adjacent to the spawner 
     *  Character can destroy a zombie spawner if they have a weapon and are cardinally adjacent 
     *  to spawner 
     */
    //can input coolDown as argument constructor, can be done in factory
    public Spawner(Position current, String type, DungeonMapAPI map, Map<String, Integer> spawnCoolDowns){
        super(map, current, type);
        this.spawnCoolDowns = spawnCoolDowns;
        roundsLeftToSpawn = new HashMap<>(spawnCoolDowns);
    }

    @Override
    public void interact() throws InvalidActionException{
        Player player = getGame().getPlayer();
        if (!Position.isAdjacent(player.getPosition(), getPosition()))
            throw new InvalidActionException("not cardinally adjacent to the spawner");
        for (BattleGear battleGear :  DungeonMap.shallowClone(player.getBattleGears())){
            if (battleGear.isWeapon()){
                battleGear.reduceDurability();
                player.getBattleStat().removeAllDeteriorated();
                getGame().removeEntity(this);
                return;
            }
        }
        throw new InvalidActionException("player does not have a weapon and attempts to destroy a spawner");
    }

    public void spawn(){
        for (String type : roundsLeftToSpawn.keySet()){
            roundsLeftToSpawn.put(type, roundsLeftToSpawn.get(type) - 1);
            if (roundsLeftToSpawn.get(type) == 0){
                DungeonMapAPI game = getGame();
                FactoryFront factory = game.getFactory();
                for (int i = 0; i <  getPosition().getAdjacentPositions().size(); i++){
                    if (i%2 != 0){
                        Position tryPosition = getPosition().getAdjacentPositions().get(i);
                        Enemy newEnemy;
                        if ((new Random()).nextInt(2) == 1)
                            newEnemy = factory.makeEnemy(type, tryPosition, new ArrayList<>(Arrays.asList("armour")));
                        else    
                            newEnemy = factory.makeEnemy(type, tryPosition);
                        if (getGame().canBeInPosition(newEnemy, tryPosition)){
                            game.addEntity(newEnemy);
                            roundsLeftToSpawn.put(type, spawnCoolDowns.get(type));
                            return;
                        }
                    }
                }
            }   
        }
    }
}

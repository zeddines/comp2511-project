package dungeonmania.entityfactory;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import dungeonmania.entity.creature.*;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class CreatureFactory extends PrimaryFactory {

    public static final String[] movingEntites = {"spider", "zombie_toast", "mercenary", "player", "hydra", "assassin"};

    public CreatureFactory(FactoryFront factoryFront) {
        super(movingEntites, factoryFront);
    }

    @Override
    public Entity build(JSONObject entityContents) {
        Position position = new Position(entityContents.getInt("x"), entityContents.getInt("y"));
        String type = entityContents.getString("type");
        DungeonMap map = getMap();

        if (type.equals("player")){
            Player player = new Player(position, type, map);    
            player.setBattleStat(makeBattleState(player));
            return player; 
        }
        else{
            return makeEnemy(type, position);            
        }
    }

    public Enemy makeEnemy(String type, Position position){
        DungeonMap map = getMap();
        switch(type){
            case "spider":
                NonInteractableEnemy spider =  new NonInteractableEnemy(position, type, map);
                spider.setBattleStat(makeBattleState(spider));
                spider.setMovement(null);
                return spider;
            case "zombie_toast":
                NonInteractableEnemy zombieToast =  new NonInteractableEnemy(position, type, map);
                zombieToast.setBattleStat(makeBattleState(zombieToast));
                zombieToast.setMovement(null);
                return zombieToast;
            case "mercenary":
                InteractableEnemy mercenary =  new InteractableEnemy(position, type, map);
                mercenary.setBattleStat(makeBattleState(mercenary));
                mercenary.addItemsRequiredToBribe(Arrays.asList("treasure"));
                mercenary.addItemsRequiredToBribe(Arrays.asList("sun_stone"));
                mercenary.setMovement(null);
                return mercenary;
            case "assassin":
                InteractableEnemy assassin =  new InteractableEnemy(position, type, map);
                assassin.setBattleStat(makeBattleState(assassin));
                assassin.addItemsRequiredToBribe(Arrays.asList("treasure", "one_ring"));
                assassin.addItemsRequiredToBribe(Arrays.asList("sun_stone", "one_ring"));
                assassin.setMovement(null);
                return assassin;
            case "hydra":
                NonInteractableEnemy hydra =  new NonInteractableEnemy(position, type, map);
                hydra.setBattleStat(makeBattleState(hydra));
                hydra.setMovement(null);
                return hydra;
            default:
                return null;   
        }        
    }

    private BattleStat makeBattleState(Creature creature){
        if (getDifficulty().equals("Peaceful"))
            return new PeacefulBattleStat(creature, 10, 0, 0);
        else{
            switch(creature.getType()){
                case "spider":
                    return new StandardBattleStat(creature, 5, 5, 0);
                case "zombie_toast":
                    return new StandardBattleStat(creature, 10, 2, 0);
                case "mercenary":
                    return new StandardBattleStat(creature, 10, 2, 0);
                case "assassin":
                    return new BossBattleStat(creature, 10, 4, 0);
                case "hydra":
                    return new HydraBattleStat(creature, 20, 2, 0);  
                case "player":
                    if (getDifficulty().equals("Hard")) 
                        return new RevivableBattleStat(creature, 5, 5, 1);
                    else
                        return new RevivableBattleStat(creature, 10, 10, 2);
                default:
                    return null;
            }
        }
        
    }
}

package dungeonmania.entityfactory;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import dungeonmania.entity.creature.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class MovingFactory extends PrimaryFactory {

    public static final String[] movingEntites = {"spider", "zombie_toast", "mercenary", "player", "hydra", "assassin"};

    public MovingFactory(String difficulty, DungeonMapAPI game) {
        super(movingEntites, difficulty, game);
    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        
        //CAN DEFINITELY BUILD A BATTLESTAT FACTORY
        //RIGHT NOW PeacefulBattleStat IS NOT SHOWN HERE, IF GAME MODE IS EASY THEN USE PeacefulBattleStat
        //MAKE A BATTLESTAT FACTORY TO ADD FLEXIBILITY
        if (type.equals("spider")){
            NonInteractableEnemy spider =  new NonInteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            spider.setBattleStat(new StandardBattleStat(spider, 5, 5, 0));
            spider.setMovement(null);
            return spider;
        }
        else if (type.equals("zombie_toast")){
            NonInteractableEnemy zombieToast =  new NonInteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            zombieToast.setBattleStat(new StandardBattleStat(zombieToast, 5, 5, 0));
            zombieToast.setMovement(null);
            return zombieToast;
        }
        else if (type.equals("mercenary")){
            InteractableEnemy mercenary =  new InteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            mercenary.setBattleStat(new StandardBattleStat(mercenary, 10, 0.1, 0));
            mercenary.setItemsRequiredToBribe(Arrays.asList("treasure", "treasure"));
            mercenary.setMovement(null);
            return mercenary;
        }
        else if (type.equals("assassin")){
            InteractableEnemy assassin =  new InteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            assassin.setBattleStat(new BossBattleStat(assassin, 10, 0.1, 0));
            assassin.setItemsRequiredToBribe(Arrays.asList("treasure", "treasure", "one_ring"));
            assassin.setMovement(null);
            return assassin;
        }
        else if (type.equals("hydra")){
            NonInteractableEnemy hydra =  new NonInteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            hydra.setBattleStat(new HydraBattleStat(hydra, 5, 5, 0));
            hydra.setMovement(null);
            return hydra;
        }

        else if (type.equals("player")){
            Player player = new Player(map, type, new Position(entityContents.getInt("x"), entityContents.getInt("y")));    
            player.setBattleStat(new RevivableBattleStat(player, 10, 10, 2));
            return player;            
        }
        return null;
    }

    public Enemy makeEnemy(String type, Position current, DungeonMapAPI map, ArrayList<Object> enemies){
        switch(type){
            case "spider":
                NonInteractableEnemy spider = new NonInteractableEnemy(current, type, map); 
                enemies.add(spider);
                return spider;
            case "zombie_toast":
                NonInteractableEnemy zombieToast = new NonInteractableEnemy(current, type, map);
                enemies.add(zombieToast); 
                return zombieToast;
            case "mercenary":
                InteractableEnemy mercenary = new InteractableEnemy(current, type, map);
                enemies.add(mercenary);
                return mercenary;
            case "assassin":
                InteractableEnemy assassin = new InteractableEnemy(current, type, map);
                enemies.add(assassin);
                return assassin;
            case "hydra":
                NonInteractableEnemy hydra = new NonInteractableEnemy(current, type, map);
                enemies.add(hydra); 
                return hydra;
            default:
                return null;   
        }        
    }

    public Player makePlayer(String type, Position current, DungeonMapAPI map){
        Player player = new Player(map, type, current);
        return player;
    }
    
}

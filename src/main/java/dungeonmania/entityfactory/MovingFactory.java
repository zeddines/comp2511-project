package dungeonmania.entityfactory;
import java.util.ArrayList;

import org.json.JSONObject;
import dungeonmania.entity.creature.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.util.*;

public class MovingFactory extends PrimaryFactory {

    public static final String[] movingEntites = {"spider", "zombie_toast", "mercenary", "player"};

    public MovingFactory(String difficulty) {
        super(movingEntites, difficulty);

    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        if (type.equals("spider"))
            return new Spider(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);     
        else if (type.equals("zombie_toast"))
            return new ZombieToast(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map); 
        else if (type.equals("player"))
            return new Player(map, type, new Position(entityContents.getInt("x"), entityContents.getInt("y")));    
        else 
            return new Mercenary(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);  
    }

    public Enemy makeEnemy(String type, Position current, DungeonMapAPI map, ArrayList<Object> enemies){
        switch(type){
            case "spider":
                Spider spider = new Spider(current, type, map); 
                enemies.add(spider);
                return spider;
            case "zombie_toast":
                ZombieToast zombieToast = new ZombieToast(current, type, map);
                enemies.add(zombieToast); 
                return zombieToast;
            default:
                Mercenary mercenary = new Mercenary(current, type, map);
                enemies.add(mercenary);
                return mercenary;
        }
    }

    public Player makePlayer(String type, Position current, DungeonMapAPI map){
        Player player = new Player(map, type, current);
        return player;
    }
    
}

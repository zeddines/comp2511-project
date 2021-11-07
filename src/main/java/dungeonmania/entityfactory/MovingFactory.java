package dungeonmania.entityfactory;
import org.json.JSONObject;
import dungeonmania.entity.creature.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class MovingFactory extends PrimaryFactory {

    public static final String[] movingEntites = {"spider", "zombie_toast", "mercenary", "player"};

    public MovingFactory() {
        super(movingEntites);

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

    public makeEnemy(String type, DungeonMapAPI map){
        switch(type){
            case "spider":
                return new Spider(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map); 
            case "zombie_toast":
                return new ZombieToast(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
            default:
                return new Mercenary(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
        }
    }
    
}

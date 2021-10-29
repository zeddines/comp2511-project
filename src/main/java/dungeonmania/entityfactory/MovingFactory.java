package dungeonmania.entityfactory;
import org.json.JSONObject;
import dungeonmania.entity.creature.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class MovingFactory extends PrimaryFactory {

    public static final String[] movingEntites = {"spider", "zombie_toast", "mercenary"};

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
        else 
            return new Mercenary(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);  
    }
    
}

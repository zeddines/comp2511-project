package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.entity.square.*;
import org.json.JSONObject;

public class StaticFactory extends PrimaryFactory {

    public static String[] staticEntities = {"wall", "exit", "boulder", "switch", "door", "portal", "zombie_toast_spawner"}; 
    
    public StaticFactory() {
        super(staticEntities);
    }

    @Override
    public Entity build(JSONObject entityContents) {
        String type = entityContents.getString("type");
        if (type.equals("wall"))
            return new Wall(entityContents);
        else if (type.equals("exit"))
            return new Exit(entityContents);
        else if(type.equals("boulder"))
            return new Boulder(entityContents);
        else if(type.equals("switch"))
            return new FloorSwitch(entityContents);
        else if(type.equals("door"))
            return new Door(entityContents);
        else
            return new ZombieToastSpawner(entityContents);
    }
}

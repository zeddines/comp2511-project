package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.entity.square.*;
import dungeonmania.map.DungeonMapAPI;

import org.json.JSONObject;
import dungeonmania.util.*;

public class StaticFactory extends PrimaryFactory {

    public static String[] staticEntities = {"wall", "exit", "boulder", "switch", "door", "portal", "zombie_toast_spawner"}; 
    
    public StaticFactory() {
        super(staticEntities);
    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        if (type.equals("wall"))
            return new Wall(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("exit"))
            return new Exit(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if(type.equals("boulder"))
            return new Boulder(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if(type.equals("switch"))
            return new FloorSwitch(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if(type.equals("door"))
            return new Door(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), entityContents.getString("key"), map);
        else
            return new ZombieToastSpawner(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
    }
    
    public makeStatic(String type, Position current, DungeonMapAPI map){
        if (type.equals("wall"))
            return new Wall(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
        else if (type.equals("exit"))
            return new Exit(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
        else if(type.equals("boulder"))
            return new Boulder(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
        else if(type.equals("switch"))
            return new FloorSwitch(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
        else if(type.equals("door"))
            return new Door(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), type.getString("key"), map);
        else if (type.equals("portal"))
            return new Portal(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
        else 
            return new ZombieToastSpawner(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
    }
}

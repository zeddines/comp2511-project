package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.entity.square.*;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;

import java.util.HashMap;

import org.json.JSONObject;
import dungeonmania.util.*;

public class StaticFactory extends PrimaryFactory {

    public static String[] staticEntities = {"wall", "exit", "boulder", "switch", "door", "portal", "zombie_toast_spawner"}; 
    
    public StaticFactory(FactoryFront factory) {
        super(staticEntities, factory);
    }

    @Override
    public Entity build(JSONObject entityContents) {
        String type = entityContents.getString("type");
        Position position = new Position(entityContents.getInt("x"), entityContents.getInt("y"));
        DungeonMap map = getMap();
        
        //TODO ADD Swamp Tile and test it in json
        switch(type){
            case "wall":
                return new Wall(position, type, map);
            case "exit":
                return new Exit(position, type, map);
            case"boulder":
                return new Boulder(position, type, map);
            case "switch":
                return new FloorSwitch(position, type, map);
            case "door":
                return new Door(position, type, entityContents.getInt("key"),  map);
            case "portal":
                return new Portal(position, type, entityContents.getString("colour"), map);
            case "zombie_toast_spawner":
                return new Spawner(position, type, map, makeSpawningHashMap());
            default:
                return null;
        }
    }
    
    private HashMap<String, Integer> makeSpawningHashMap(){
        HashMap<String, Integer> spawnHashMap = new HashMap<>();
        switch(getDifficulty()){
            case "Hard":
                spawnHashMap.put("zombie_toast", 15);
                spawnHashMap.put("hydra", 50);
                return spawnHashMap;
            default:
                spawnHashMap.put("zombie_toast", 20);
                return spawnHashMap;
        }
    }
}

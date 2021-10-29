package dungeonmania.entityfactory;

import java.util.Arrays;
import java.util.List;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;

import org.json.JSONObject;

abstract public class PrimaryFactory implements FactoryExtendAPI {

    private List<String> entities;

    public PrimaryFactory(String[] entities) {
        this.entities = Arrays.asList(entities);
    }

    public boolean checkType(String type) {
        return entities.contains(type);
    }

    abstract public Entity build(JSONObject entityContents, DungeonMapAPI map);
    
}

package dungeonmania.entityfactory;

import java.util.Arrays;
import java.util.List;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;

import org.json.JSONObject;

abstract public class PrimaryFactory {

    private List<String> entities;
    private FactoryFront entityFactory;

    public PrimaryFactory(String[] entities, FactoryFront factory) {
        this.entities = Arrays.asList(entities);
        this.entityFactory = factory;
    }

    public boolean checkType(String type) {
        return entities.contains(type);
    }

    public FactoryFront getEntityFactory(){
        return entityFactory;
    }

    public String getDifficulty(){
        return entityFactory.getDifficulty();
    }

    public DungeonMap getMap(){
        return entityFactory.getMap();
    }

    abstract public Entity build(JSONObject entityContents);
}

package dungeonmania.entityfactory;

import java.util.Arrays;
import java.util.List;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;

import org.json.JSONObject;

abstract public class PrimaryFactory implements FactoryExtendAPI {

    private List<String> entities;
    private String difficulty;
    private DungeonMapAPI game;

    public PrimaryFactory(String[] entities, String difficulty, DungeonMapAPI game) {
        this.entities = Arrays.asList(entities);
        this.difficulty = difficulty;
        this.game = game;
    }

    public boolean checkType(String type) {
        return entities.contains(type);
    }

    public String getDifficulty(){
        return difficulty;
    }

    abstract public Entity build(JSONObject entityContents, DungeonMapAPI map);
    
}

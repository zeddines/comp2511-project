package dungeonmania.map;

import dungeonmania.entityfactory.FactoryAPI;
import dungeonmania.entityfactory.FactoryFront;
import dungeonmania.entityfactory.PrimaryFactory;
import dungeonmania.util.*;
import java.util.List;

import java.io.IOException;

import org.json.*;

public class MapBuilder implements MapBuilderAPI {

    private FactoryAPI entityFactory = new FactoryFront();

    public void build(String dungeonName, String gameMode) {
        DungeonMap newGame = new DungeonMap();
        try {
            String mapString = FileLoader.loadResourceFile("/dungeons/maze.json");
            JSONObject map = new JSONObject(mapString);
            JSONArray entities = map.getJSONArray("entities");  
            for (int i = 0; i < entities.length(); i++)
                newGame.addEntity(entityFactory.build(entities.getJSONObject(i)));

        } catch (IOException e) {

        }

        
        

    }
    
}

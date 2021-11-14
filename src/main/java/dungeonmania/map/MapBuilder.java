package dungeonmania.map;

import dungeonmania.entity.collectable.Armour;
import dungeonmania.entity.creature.BossBattleStat;
import dungeonmania.entity.creature.InteractableEnemy;
import dungeonmania.entity.square.Portal;
import dungeonmania.entityfactory.FactoryAPI;
import dungeonmania.entityfactory.FactoryFront;
import dungeonmania.entityfactory.PrimaryFactory;
import dungeonmania.util.*;
import java.util.List;

import java.io.IOException;

import org.json.*;

public class MapBuilder implements MapBuilderAPI {

    private FactoryFront entityFactory;

    public DungeonMap build(String dungeonName, String gameMode) {
        DungeonMap newGame = new DungeonMap();
        entityFactory = new FactoryFront(gameMode, newGame);
        newGame.setFactory(entityFactory);
        try {
            String mapString = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");
            JSONObject map = new JSONObject(mapString);
            JSONArray entities = map.getJSONArray("entities");  
            for (int i = 0; i < entities.length(); i++) {
                newGame.addEntity(entityFactory.build(entities.getJSONObject(i)));
            }         
            //TESTING !!!!!!!!!!!!!!!a
            
            ////TESTING END
            // String goals = "";
            // JSONObject goalConditions = map.getJSONObject("goal-condition");
            // if (goalConditions.has("subgoals")) {
            //     String goalConnected = goalConditions.getString("goal");
            //     JSONArray subGoals = goalConditions.getJSONArray("subgoals");
            //     for (int i = 0 ; i < subGoals.length(); i++) {
            //        if (i < subGoals.length() - 1 )
            //             goals = goals + subGoals.getJSONObject(i).getString("goal") + goalConnected;
            //        else {
            //            goals = goals + subGoals.getJSONObject(i).getString("goal");
            //        } 
            //     }

            // } else {
            //     goals = goals + goalConditions.getString("goal");
            // }
            // newGame.setGoals(goals);
            return newGame;

        } catch (IOException e) {
            return null;

        }                

    }
    
}

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

    private FactoryAPI entityFactory;

    public DungeonMap build(String dungeonName, String gameMode) {
        DungeonMap newGame = new DungeonMap();
        entityFactory = new FactoryFront(gameMode, newGame);
        try {
            String mapString = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");
            JSONObject map = new JSONObject(mapString);
            JSONArray entities = map.getJSONArray("entities");  
            for (int i = 0; i < entities.length(); i++) {
                newGame.addEntity(entityFactory.build(entities.getJSONObject(i), newGame));
            }         

            // InteractableEnemy assassin = new InteractableEnemy(new Position(6, 5), "assassin", newGame);
            // assassin.setBattleStat(new BossBattleStat(assassin, 5, 5, 0));
            // assassin.setMovement(null);
            // newGame.addEntity(assassin);
            // mercenary.addCollectable(new Armour("armour", newGame, mercenary));
            // newGame.addEntity(mercenary);
            //TESTING !!!!!!!!!!!!!!!a
            
            // newGame.addEntity(new Portal(new Position(5,5), "portal", "BLUE", newGame));
            // newGame.addEntity(new Portal(new Position(5,9), "portal", "BLUE", newGame));
            
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

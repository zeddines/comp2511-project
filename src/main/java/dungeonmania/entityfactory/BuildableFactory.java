package dungeonmania.entityfactory;

import java.util.ArrayList;

import org.json.JSONObject;
import dungeonmania.entity.buildable.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class BuildableFactory extends PrimaryFactory {

    public static final String[] buildables = {"bow", "shield"};    

    ArrayList<Collectable> builtItems = new ArrayList<>();

    public BuildableFactory(String difficulty, DungeonMapAPI map) {
        super(buildables, difficulty, map);
    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        // if (type.equals("bow"))
        //     //bow and shields don't have locations now
        //     return new Bow(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map); 
        // else
        //     return new Shield(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        return null;

    }

    public Collectable makeBuildable(String type, DungeonMapAPI map, DungeonResponse dungeonResponse, Player player){
        
        //Collectable buildables = new ArrayList<>();
        switch(type){
            case "bow":
                if (countBuildableItems("wood", dungeonResponse) >= 1 && countBuildableItems("arrow", dungeonResponse) >= 3){
                    Bow bow = new Bow(type, map, player);
                    dungeonResponse.getBuildables().add("bow");
                    dungeonResponse.getInventory().remove("wood");
                    dungeonResponse.getInventory().remove("arrow");
                    dungeonResponse.getInventory().remove("arrow");
                    dungeonResponse.getInventory().remove("arrow");                    
                    return bow;
                }
            case "shield":
                if (countBuildableItems("wood", dungeonResponse) >= 2){
                    if (countBuildableItems("key", dungeonResponse) >= 1){
                        Shield shield = new Shield(type, map, player);
                        dungeonResponse.getBuildables().add("shield");
                        dungeonResponse.getInventory().remove("wood");
                        dungeonResponse.getInventory().remove("wood");    
                        dungeonResponse.getInventory().remove("key");                       
                        return shield;
                    }
                    else if (countBuildableItems("treasure", dungeonResponse) >= 1){
                        
                        Shield shield = new Shield(type, map, player);
                        dungeonResponse.getBuildables().add("shield");
                        dungeonResponse.getInventory().remove("wood");
                        dungeonResponse.getInventory().remove("wood");  
                        dungeonResponse.getInventory().remove("treasure");                         
                        return shield;
                    }
                }                                    
            //     if (nonBattleItems.contains("wood") && nonBattleItems.contains("arrow")){
            //         nonBattleItems.remove("arrow");
            //         nonBattleItems.remove("wood");
            //         Bow bow = new Bow(type, map, owner);
            //         buildables.add(bow);
            //         return bow;
            //     }
            // case "Shield":
            //     if (nonBattleItems.contains("wood") && nonBattleItems.contains("arrow")){
            //         nonBattleItems.remove("arrow");
            //         nonBattleItems.remove("wood");
            //         Shield shield = new Shield(type, map, owner);
            //         buildables.add(shield);
            //         return shield;
            //     }
        }
        return null;
    }  
    
    public int countBuildableItems(String type, DungeonResponse dungeonResponse){  
        int count = 0;

        for (ItemResponse i: dungeonResponse.getInventory()){
            if (i.getType().equals(type)){
                count++;
            }
        }
        return count;
        //return dungeonResponse.getInventory().stream().filter(item -> item.getType().equals(type).count());
    }
}

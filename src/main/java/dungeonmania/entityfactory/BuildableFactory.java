package dungeonmania.entityfactory;

import java.util.ArrayList;

import org.json.JSONObject;
import dungeonmania.entity.buildable.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;
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

    public Collectable makeBuildable(String type, DungeonMapAPI map){
        
        //Collectable buildables = new ArrayList<>();
        switch(type){
            case "bow":
                if (countBuildableItems("wood") >= 1 && countBuildableItems("arrow") >= 3){
                    Bow bow = new Bow(type, map, owner);
                    builtItems.add(bow);
                    return bow;
                }
            case "shield":
                if (countBuildableItems("wood") >= 1){
                    if (countBuildableItems("key") >= 1 || countBuildableItems("treasure") >= 1){
                        Shield shield = new Shield(type, map, owner);
                        builtItems.add(shield);
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
    
    public int countBuildableItems(String type){
        return (int) this.collectables.stream().filter(item -> item.getType().equals(type).count());
    }
}

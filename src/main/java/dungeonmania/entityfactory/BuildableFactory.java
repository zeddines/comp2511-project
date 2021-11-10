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

    public BuildableFactory(String difficulty) {
        super(buildables, difficulty);
    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        if (type.equals("bow"))
            //bow and shields don't have locations now
            return new Bow(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map); 
        else
            return new Shield(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);

    }

    public Collectable makeBuildable(String type, DungeonMapAPI map, ArrayList<Collectable> nonBattleItems, Creature owner){
        owner.getType();
        Collectable buildables = new ArrayList<>();
        switch(type){
            case "Bow":
                if (nonBattleItems.contains("wood") && nonBattleItems.contains("arrow")){
                    nonBattleItems.remove("arrow");
                    nonBattleItems.remove("wood");
                    Bow bow = new Bow(type, map, owner);
                    buildables.add(bow);
                    return bow;
                }
            case "Shield":
                if (nonBattleItems.contains("wood") && nonBattleItems.contains("arrow")){
                    nonBattleItems.remove("arrow");
                    nonBattleItems.remove("wood");
                    Shield shield = new Shield(type, map, owner);
                    buildables.add(shield);
                    return shield;
                }
        }
    }    
}

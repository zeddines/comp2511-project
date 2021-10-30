package dungeonmania.entityfactory;

import org.json.JSONObject;
import dungeonmania.entity.buildable.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;



public class BuildableFactory /*extends PrimaryFactory*/ {

    public static final String[] buildables = {"bow", "shield"};

    public BuildableFactory() {
        //super(buildables);
    }

    /*@Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        if (type.equals("bow"))
            //bow and shields don't have locations now
            return new Bow(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map); 
        else
            return new Shield(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);

    }*/
    
}

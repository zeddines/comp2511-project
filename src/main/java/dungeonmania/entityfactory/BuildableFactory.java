package dungeonmania.entityfactory;

import org.json.JSONObject;
import dungeonmania.entity.buildable.*;
import dungeonmania.entity.*;



public class BuildableFactory extends PrimaryFactory {

    public static final String[] buildables = {"bow", "shield"};

    public BuildableFactory() {
        super(buildables);
    }

    @Override
    public Entity build(JSONObject entityContents) {
        String type = entityContents.getString("type");
        if (type.equals("bow"))
            return new Bow(entityContents);
        else
            return new Shield(entityContents);

    }
    
}

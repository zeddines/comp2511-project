package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;

import org.json.JSONObject;

public interface FactoryAPI {

    public Entity build(JSONObject entityContents, DungeonMapAPI map);
}

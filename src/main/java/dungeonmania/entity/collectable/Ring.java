package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;
import dungeonmania.map.DungeonMapAPI;

public class Ring extends Entity implements EntityAPI {
    /**
     * if char is killed, it respawns will full health 
     * once used, the One Ring is discarded 
     */
    public Ring(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, false, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

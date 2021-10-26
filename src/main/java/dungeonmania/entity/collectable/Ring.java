package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;

public class Ring extends Entity implements EntityAPI {
    /**
     * if char is killed, it respawns will full health 
     * once used, the One Ring is discarded 
     */
    public Ring(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

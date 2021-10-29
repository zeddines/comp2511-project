package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;
import dungeonmania.map.DungeonMapAPI;

public class Shield extends Entity implements EntityAPI {
    private int durability; 
    private int remainingUses; 

    /**
     * crafted with 2 wood and 1 treasure or key 
     * shields decrease the effect of enemy attack 
     * each shield has specific durability that dictates the number of times it can be used 
     * before it deteriorates 
     */
    public Shield(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, true, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

    /*public void modifyDefence() {
        return; 
    }*/
}

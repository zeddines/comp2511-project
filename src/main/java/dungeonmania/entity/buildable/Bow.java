package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;
import dungeonmania.map.DungeonMapAPI;

public class Bow extends Entity implements EntityAPI {
    public int durability; 
    public int remainingUses; 
    
    /**
     * crafted with 1 wood and 3 arrows 
     * allows char to attack twice in a single round 
     */
    public Bow(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, true, map); 
    }
    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

    /*public void modifyAttack() {
        return; 
    }*/
}

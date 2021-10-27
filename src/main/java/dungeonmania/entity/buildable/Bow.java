package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;

public class Bow extends Entity implements EntityAPI {
    public int durability; 
    public int remainingUses; 
    
    /**
     * crafted with 1 wood and 3 arrows 
     * allows char to attack twice in a single round 
     */
    public Bow(JSONObject entityContents) {
        super(entityContents); 
    }
    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

    /*public void modifyAttack() {
        return; 
    }*/
}

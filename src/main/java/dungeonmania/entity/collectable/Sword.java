package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;

public class Sword extends Entity implements EntityAPI {
    private int durability; 
    private int remainingUses; 

    /**
     * standard meelee weapon 
     * can be collected y chr and used in battles
     * each sword has a sp ecific durability that dictates the num 
     * of times it can be used before it deteriorates 
     */
    public Sword(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
    
    /*public void modifyAttack() {
        return; 
    }

    @Override
    public void collideAction(String player) { // Player player 
        return;
    }*/
}

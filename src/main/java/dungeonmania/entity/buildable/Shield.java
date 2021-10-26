package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.util.*;

import dungeonmania.entity.interfaces.*;

public class Shield extends Buildable implements Guard, Consumable {
    private int durability; 
    private int remainingUses; 

    /**
     * crafted with 2 wood and 1 treasure or key 
     * shields decrease the effect of enemy attack 
     * each shield has specific durability that dictates the number of times it can be used 
     * before it deteriorates 
     */
    public Shield() {
        super(); 
    }

    public void modifyDefence() {
        return; 
    }
}

package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.util.*;

import dungeonmania.entity.interfaces.*;

public class Bow extends Buildable implements Weapon, Consumable {
    public int durability; 
    public int remainingUses; 
    
    /**
     * crafted with 1 wood and 3 arrows 
     * allows char to attack twice in a single round 
     */
    public Bow() {
        super(); 
    }

    public void modifyAttack() {
        return; 
    }
}

package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class HealthPotion extends Collectable {
    /**
     * when character picks up a health potion, they may consume it at any time 
     * and will immediately regenerate to full health 
     * health potions may only be consumed once 
     */
    public HealthPotion() {
        super(); 
    }

}

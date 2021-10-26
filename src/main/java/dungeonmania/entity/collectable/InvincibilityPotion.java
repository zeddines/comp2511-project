package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class InvincibilityPotion extends Collectable {
    private int duration; 

    /**
     * when character picks up invincibility potion, it may be used at an ytime 
     * any battles that occur when the char has the effects of the potion end immediately 
     * with char immediately 
     * all enemies will run away from char when they are invincible 
     * effects only last for a limited time 
     */
    public InvincibilityPotion() {
        super(); 
    }

    public void use() {
        return; 
    }
}

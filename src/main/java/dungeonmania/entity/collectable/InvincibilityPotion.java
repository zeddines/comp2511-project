package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class InvincibilityPotion extends Entity implements EntityAPI {
    private int duration; 

    /**
     * when character picks up invincibility potion, it may be used at an ytime 
     * any battles that occur when the char has the effects of the potion end immediately 
     * with char immediately 
     * all enemies will run away from char when they are invincible 
     * effects only last for a limited time 
     */
    public InvincibilityPotion(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

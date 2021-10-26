package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class HealthPotion extends Entity implements EntityAPI {
    /**
     * when character picks up a health potion, they may consume it at any time 
     * and will immediately regenerate to full health 
     * health potions may only be consumed once 
     */
    public HealthPotion(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

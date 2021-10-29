package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class HealthPotion extends Entity implements EntityAPI {
    /**
     * when character picks up a health potion, they may consume it at any time 
     * and will immediately regenerate to full health 
     * health potions may only be consumed once 
     */
    public HealthPotion(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, false, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

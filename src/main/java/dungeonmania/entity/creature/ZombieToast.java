package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class ZombieToast extends Entity {
    /**
     * Zombies spawn at zombie spawners and move in random directions 
     * Zombies are limited by the same movement constraints as character
     * except portals have no effect on then  
     */
    public ZombieToast(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

}

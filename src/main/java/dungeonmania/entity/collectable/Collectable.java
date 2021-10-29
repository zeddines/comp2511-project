package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Collectable extends Entity {
    // private Player owner; 
    private boolean inInventory; 

    public Collectable(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, false, map); 
        this.inInventory = false;
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
   
    /**
     * remove the collectable from undeon
     * set position to null and add to player inventory 
     * @param player    Player player 
     */
    /*public void collideAction(String player) { // Player player 
        return;
    }

    public void removeFromInventory() {
        return; 
    }*/
}

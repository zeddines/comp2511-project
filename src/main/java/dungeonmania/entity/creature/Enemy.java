package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Enemy extends Entity {
    
    public Enemy(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, true, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

    /**
     * Initiate a battle in which it will remove
     * itself from collide and action arrayList and 
     * add to battling arrayList in game object 
     * @param player - Player object 
     */
    /*public void collideAction(String player) {      // Player player 
        return; 
    }

    public void regularAction() {
        return; 
    }*/

    // movement fn 

}

package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Key extends Collectable{
   //private String door; 

    /**
     * can be picked up by the player when they move into the square containing it
     * character can only carry one key at a time, and only one door has a lock that 
     * fits that key
     * the key also disappears once it is used to open its corresponding door  
     */
    public Key(Position current, String type, DungeonMapAPI map) {
        super(map, type, current, false);
        //this.do = entityContents.getString("key");
    }
}

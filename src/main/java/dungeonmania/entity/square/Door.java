package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Door extends Entity {
    private String key; 

    /**
     *  Exists in conjunction with a single key that can open it 
     *  If character holds the key, they can open the door by moving through it
     *  Remains open once opened 
     */
    public Door(Position current, String type, String key, DungeonMapAPI map) {
        super(current, type, true, false, map); 
        this.key = key;
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

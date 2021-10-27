package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Door extends Entity {
    private String key; 

    /**
     *  Exists in conjunction with a single key that can open it 
     *  If character holds the key, they can open the door by moving through it
     *  Remains open once opened 
     */
    public Door(JSONObject entityContents) {
        super(entityContents); 
        key = entityContents.getString("key");
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

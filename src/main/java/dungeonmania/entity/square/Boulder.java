package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Boulder extends Entity {

    /**
     *  Boulder acts like a wall in most cases. 
     *  Can be pushed by character into cardinally adjacent squares
     *  Character can only push one boulder at a time 
     */
    public Boulder(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, false, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

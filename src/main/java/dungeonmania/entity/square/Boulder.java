package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Boulder extends Entity {

    /**
     *  Boulder acts like a wall in most cases. 
     *  Can be pushed by character into cardinally adjacent squares
     *  Character can only push one boulder at a time 
     */
    public Boulder(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

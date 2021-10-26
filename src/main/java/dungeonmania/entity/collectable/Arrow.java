package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Arrow extends Entity implements EntityAPI {
    /**
     * can be picked up by player 
     */
    public Arrow(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

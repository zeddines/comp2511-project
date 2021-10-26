package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Portal extends Entity {
    public Portal(Position currentPosition) {
        super(currentPosition);

    }

    /**
     *  Teleports entities to a corresponding portal 
     */
    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
    
}

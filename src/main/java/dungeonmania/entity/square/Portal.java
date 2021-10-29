package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Portal extends Entity {

    String colour;

    /**
     *  Teleports entities to a corresponding portal 
     * colour refers to the corresponding portal -> will need to fix this
     */
    public Portal(Position current, String type, String colour, DungeonMapAPI map) {
        super(current, type, false, false, map); 
        this.colour = colour;
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
    
}

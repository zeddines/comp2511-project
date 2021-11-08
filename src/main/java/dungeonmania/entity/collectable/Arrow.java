package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Arrow extends Collectable{
    /**
     * can be picked up by player 
     */
    public Arrow(Position current, String type, DungeonMapAPI map) {
        super(map, type, current);
    }
}

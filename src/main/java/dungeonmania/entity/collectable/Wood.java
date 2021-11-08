package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Wood extends Collectable {
    /**
     * can be collected by player 
     */
    public Wood(Position current, String type, DungeonMapAPI map) {
        super(map, type, current);
    }
}

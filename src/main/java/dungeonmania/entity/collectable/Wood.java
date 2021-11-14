package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Wood extends Collectable {
    /**
     * can be collected by player 
     */

    public Wood(Position position, String type, DungeonMapAPI map) {
        super(map, type, position);
    }

    public Wood(Creature owner, String type, DungeonMapAPI map) {
        super(map, type, owner);
    }
}

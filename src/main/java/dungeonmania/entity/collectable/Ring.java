package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Player;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.map.DungeonMapAPI;

public class Ring extends Collectable{
    /**
     * if char is killed, it respawns will full health 
     * once used, the One Ring is discarded 
     */
    
    public Ring(String type, DungeonMapAPI map, Creature owner) {
        super(map, type, owner);
    }
}

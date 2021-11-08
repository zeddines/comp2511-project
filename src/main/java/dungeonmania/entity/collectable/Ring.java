package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.map.DungeonMapAPI;

public class Ring extends Collectable{
    /**
     * if char is killed, it respawns will full health 
     * once used, the One Ring is discarded 
     */


    //TODO right now ring won't be appearing on the map since it is given as a reward after defeating an enemy 
    //so rn we should always use the second constructor
    // public Ring(Position current, String type, DungeonMapAPI map) {
    //     super(map, type, null);
    // }
    
    public Ring(String type, DungeonMapAPI map, Player owner) {
        super(map, type, owner);
    }
}

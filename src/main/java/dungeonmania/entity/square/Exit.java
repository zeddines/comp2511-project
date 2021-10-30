package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Exit extends Entity {    
    /**
     *  Puzzle is complete if character goes through it 
     */
    public Exit(Position current, String type, DungeonMapAPI map) {
        super(map, current, type, false);
    }

}

package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Exit extends Entity {    
    /**
     *  Puzzle is complete if character goes through it 
     */
    public Exit(Position current, String type, DungeonMapAPI map) {
        super(current, type, false, false, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

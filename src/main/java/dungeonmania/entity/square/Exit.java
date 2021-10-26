package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Exit extends Entity {    
    /**
     *  Puzzle is complete if character goes through it 
     */
    public Exit(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

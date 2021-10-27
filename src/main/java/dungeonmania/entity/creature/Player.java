package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Player extends Entity {
    
    public Player(JSONObject entityContents) {
        super(entityContents); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

    /*public void toItemResponse() {
        // returns itemResponse 
        return; 
    }

    public void use(String s) {
        return; 
    }

    public void move(String direction) {
        // Direction direction 
        return; 
    }*/
}

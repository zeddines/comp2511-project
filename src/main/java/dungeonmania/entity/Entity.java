package dungeonmania.entity;
import dungeonmania.util.*;
import org.json.JSONObject;
import java.lang.Integer;

abstract public class Entity implements EntityAPI {

    Position currentPosition;

    public Entity(JSONObject entityContents) {
        this.currentPosition = new Position(Integer.parseInt(entityContents.getString("x")), Integer.parseInt(entityContents.getString("y")));
    }

   abstract public boolean action(EntityAPI creature);

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public Position getPosition() {
        return currentPosition;
    }
    
}

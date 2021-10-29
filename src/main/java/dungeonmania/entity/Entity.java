package dungeonmania.entity;
import dungeonmania.util.*;
import org.json.JSONObject;
import java.lang.Integer;
import dungeonmania.response.models.*;

abstract public class Entity implements EntityAPI {

    String iD;
    String type; 
    Position currentPosition;
    public static Integer numEntities = 0;
    Boolean isInteractable;

    public Entity(JSONObject entityContents) {
        this.currentPosition = new Position(entityContents.getInt("x"), entityContents.getInt("y"));
        this.type = entityContents.getString("type");
        this.iD = numEntities.toString();
        this.isInteractable = false;
        numEntities++;

    }

    abstract public boolean action(EntityAPI creature);

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public Position getPosition() {
        return currentPosition;
    }

    public EntityResponse getInfo() {
        return new EntityResponse(iD, type, currentPosition, isInteractable);
    }
    
}

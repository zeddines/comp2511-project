package dungeonmania.entity;
import dungeonmania.util.*;
import org.json.JSONObject;
import java.lang.Integer;
import dungeonmania.response.models.*;
import dungeonmania.map.*;

abstract public class Entity implements EntityAPI {

    String iD;
    String type; 
    Position currentPosition;
    public static Integer numEntities = 0;
    Boolean isInteractable;
    Boolean dynamic;
    DungeonMapAPI dungeon;

    public Entity(Position current, String type, boolean interactable, boolean dynamic, DungeonMapAPI dungeon) {
        this.currentPosition = current;
        this.type = type;
        this.iD = numEntities.toString();
        this.isInteractable = false;
        numEntities++;
        this.isInteractable = interactable;
        this.dynamic = dynamic;
        this.dungeon = dungeon;

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

    public boolean checkDyanmic() {
        if (dynamic)
            return  true;
        else 
            return false;
    }
    
}

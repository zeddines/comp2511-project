package dungeonmania.entities;
import dungeonmania.util.*;

public class Entity implements EntityAPI {

    Position currentPosition;

    public Entity(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean action(EntityAPI character) {
        return false;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
    
}

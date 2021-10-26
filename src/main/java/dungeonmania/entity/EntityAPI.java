package dungeonmania.entity;
import dungeonmania.util.*;

public interface EntityAPI {

    public boolean action(EntityAPI creature); 
    
    public Position getPosition();
}

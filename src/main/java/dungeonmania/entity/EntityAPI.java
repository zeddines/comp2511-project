package dungeonmania.entity;
import dungeonmania.util.*;
import dungeonmania.response.models.*;

public interface EntityAPI {

    public boolean action(EntityAPI creature); 
    
    public Position getPosition();

    public EntityResponse getInfo();
}

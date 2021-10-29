package dungeonmania.entity;
import dungeonmania.util.*;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.response.models.*;

public interface EntityAPI {

    //public void action(EntityAPI creature); 
    public void action(Player player); 
    //right now don't need this as enemy won't interact with other entities except player
    //public void action(Enemy enemy); 
    
    public Position getPosition();
    public boolean isDynamic();
    public EntityResponse getInfo();

    //TODO 
    //public boolean canCoExist(Player player);
}

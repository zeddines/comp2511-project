package dungeonmania.entity;
import dungeonmania.util.*;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
import dungeonmania.response.models.*;

public interface EntityAPI {
    public void collideAction(Player player);
    public void collideAction(Boulder boulder);
    //public <T extends Entity> void collideAction(T entity);
    public void leaveAction(Player player);
    public void leaveAction(Boulder boulder); 
    public Position getPosition();
    public String getId();
    public void setPosition(Position position);
    public String getType();
    public boolean isMovableNPC();
    public boolean isHostile();
    public boolean isInteractable();
    public EntityResponse toEntityResponse();
    public boolean canBeOnSamePosition(Player player);
    public boolean canBeOnSamePosition(Boulder boulder);
    public boolean canBeOnSamePosition(Enemy enemy);


}

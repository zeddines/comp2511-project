package dungeonmania.map;
import java.util.ArrayList;
import java.util.List;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
import dungeonmania.response.models.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public interface DungeonMapAPI {
    public List<EntityResponse> toEntityResponseList();
    public List<ItemResponse> toItemResponseList();
    public void addEntity(EntityAPI newEntity);
    public String getGoals();
    public void setGoals(String goals);

    //newly added
    public void addToBattle(Enemy enemy);
    public void addToAlly(Enemy enemy);
    public void setPlayer(Player newPlayer);
    public Player getPlayer();
    public void tick(String itemUsedId, Direction movementDirection);
    public void interact(String entityId);
    public boolean canBeInPosition(Enemy enemy, Position newPos);
    public boolean canBeInPosition(Boulder boulder, Position newPos);
    public boolean playerCanMoveToward(Direction direction);
    public void moveFromPositionTo(EntityAPI entity, Position to);
    public void removeEntity(EntityAPI entity);
    public ArrayList<EntityAPI> getAllEntitiesInMap();
    public ArrayList<Enemy> getAllies();
}

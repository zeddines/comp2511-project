package dungeonmania.map;
import java.util.ArrayList;
import java.util.List;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Effect;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
import dungeonmania.entityfactory.FactoryFront;
import dungeonmania.response.models.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public interface DungeonMapAPI {
    public List<EntityResponse> toEntityResponseList();
    public List<ItemResponse> toItemResponseList();
    public void addEntity(EntityAPI newEntity);
    public String getGoals();
    public void setGoals(String goals);
    public void addToBattle(Enemy enemy);
    public void addToAlly(Creature enemy);
    public void removeFromAlly(Creature enemy);
    public void setPlayer(Player newPlayer);
    public Player getPlayer();

    public void tick(String itemUsedId, Direction movementDirection, DungeonResponse d);
    public void interact(String entityId);    
    public List<String> buildableItems();
    public boolean canBeInPosition(Enemy enemy, Position newPos);
    public boolean canBeInPosition(Boulder boulder, Position newPos);
    public boolean playerCanMoveToward(Direction direction);
    public void moveFromPositionTo(EntityAPI entity, Position to);
    public void removeEntity(EntityAPI entity);
    public void addEffectInAction(Effect effect);
    public void removeEffectInAction(Effect effect);
    public ArrayList<EntityAPI> getAllEntitiesInMap();
    public ArrayList<Creature> getAllies();
    public FactoryFront getFactory();
    public void build(String buildable);
}

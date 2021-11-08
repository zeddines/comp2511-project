package dungeonmania.game;

import dungeonmania.entity.creature.Player;
import dungeonmania.response.models.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
public interface GameAPI {

    public String getId();   
    
    public DungeonResponse toDungeonResponse();

    public void setID(String name);

    public Player getPlayer();

    public DungeonResponse tick(String itemUsed, Direction movementDirection);
    public DungeonResponse interact(String entityId);
}

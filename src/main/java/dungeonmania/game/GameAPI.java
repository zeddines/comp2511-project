package dungeonmania.game;

import dungeonmania.entity.creature.Player;
import dungeonmania.response.models.*;
import dungeonmania.util.Position;
public interface GameAPI {

    public String getId();   
    
    public DungeonResponse getInfo();

    public void setID(String name);

    public Player getPlayer();

    public boolean checkLocation(Position check);

    public void collideAction(Player player, Position currentPosition);
 
}

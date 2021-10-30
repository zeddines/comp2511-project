package dungeonmania.map;
import java.util.List;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.response.models.*;
import dungeonmania.util.Position;

public interface DungeonMapAPI {
    public List<EntityResponse> getInfoList();
    public List<ItemResponse> getItemInfoList();
    public void addEntity(EntityAPI newEntity);
    public String getGoals();
    public void setGoals(String goals);

    //newly added
    public void addToBattle(Enemy enemy);
    public void removeEntityFromMap(EntityAPI entity);
    public void setPlayer(Player newPlayer);
    public Player getPlayer();
    public boolean checkLocation(Position check);
    public void collideAction(Player player, Position currentPosition);
}

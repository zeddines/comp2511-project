package dungeonmania.map;
import java.util.List;

import dungeonmania.entity.EntityAPI;
import dungeonmania.response.models.*;

public interface DungeonMapAPI {
    public List<EntityResponse> getInfoList();
    public void addEntity(EntityAPI newEntity);
    public String getGoals();
    public void setGoals(String goals);
    
}

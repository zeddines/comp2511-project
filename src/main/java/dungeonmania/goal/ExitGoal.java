package dungeonmania.goal;

import dungeonmania.entity.Entity;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;

import java.util.List;

public class ExitGoal implements Goals{
    @Override
    public boolean goalSatisfied(DungeonResponse d) {
        List<EntityResponse> e = d.getEntities();













        return false;
    }
}

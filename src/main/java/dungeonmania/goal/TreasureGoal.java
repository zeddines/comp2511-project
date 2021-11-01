package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;

import java.util.List;

public class TreasureGoal implements Goals {
    @Override
    public boolean goalSatisfied(DungeonResponse d) {
        List<EntityResponse> listOfEntities = d.getEntities();

        for (EntityResponse e: listOfEntities) {
            if (e.getType().equals("treasure")) {
                return false;
            }
        }

        return true;
    }
}

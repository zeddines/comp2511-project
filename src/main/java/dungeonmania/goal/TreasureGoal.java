package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;

import java.util.List;

public class TreasureGoal implements Goals {
    public TreasureGoal() {
    }

    @Override
    public String goalSatisfied(DungeonResponse d) {
        List<EntityResponse> listOfEntities = d.getEntities();

        for (EntityResponse e: listOfEntities) {
            if (e.getType().equals("treasure")) {
                return d.getGoals();
            }
        }

        return "";
    }
}

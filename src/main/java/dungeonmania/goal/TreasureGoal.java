package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;

public class TreasureGoal implements Goals {
    @Override
    public boolean goalSatisfied(DungeonResponse d) {
        return false;
    }
}

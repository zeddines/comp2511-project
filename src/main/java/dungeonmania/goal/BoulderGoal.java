package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;

public class BoulderGoal implements Goals{
    @Override
    public boolean goalSatisfied(DungeonResponse d) {
        return false;
    }
}

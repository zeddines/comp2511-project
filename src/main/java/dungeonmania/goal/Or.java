package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;

public class Or implements Goals{
    public Or() {
    }

    @Override
    public String goalSatisfied(DungeonResponse d) {
        return null;
    }

    @Override
    public String toString() {
        return "Or";
    }
}

package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;

public class And implements Goals{
    public And() {
    }

    @Override
    public String goalSatisfied(DungeonResponse d) {
        return null;
    }

    @Override
    public String toString() {
        return "And";
    }
}

package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;

import java.util.ArrayList;
import java.util.List;

public class AllGoals implements Goals{
    private List<Goals> children = new ArrayList<>();

    public void addGoal(Goals g) {
        children.add(g);
    }

    public void listGoals() {
        for (Goals g: children) {
            System.out.println(g);
        }
    }

    @Override
    public boolean goalSatisfied(DungeonResponse d) {
        for (Goals g: children) {
            if (!g.goalSatisfied(d)){
                return false;
            }
        }

        return true;
    }
}

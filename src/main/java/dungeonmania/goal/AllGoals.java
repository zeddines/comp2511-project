package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;

import java.util.ArrayList;
import java.util.List;

public class AllGoals implements Goals{
    private List<Goals> children;

    public AllGoals() {
        children = new ArrayList<>();
    }

    public void addGoal(Goals g) {
        children.add(g);
    }

    public void listGoals() {
        for (Goals g: children) {
            System.out.println(g);
        }
    }

    @Override
    public String goalSatisfied(DungeonResponse d) {
        if (children.get(0).toString().equals("And")) {
            for (Goals g: children) {
                if (!g.goalSatisfied(d).equals("")){
                    return d.getGoals();
                }
            }

            return "";
        } else if (children.get(0).toString().equals("Or")) {
            for (Goals g: children) {
                if (g.goalSatisfied(d).equals("")){
                    return "";
                }

            }

            return d.getGoals();
        }

        return "";
    }
}

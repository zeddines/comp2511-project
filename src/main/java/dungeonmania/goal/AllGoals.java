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
        for (Goals l: children) {
            System.out.println(l);
        }

        if (children.contains("And")) {
            for (Goals g: children) {
                if (!g.goalSatisfied(d).equals("")){
                    return d.getGoals();
                }
            }

            return "";
        } else if (children.contains("Or")) {
            for (Goals g: children) {
                if (g.goalSatisfied(d).equals("")){
                    return "";
                }

            }

            return d.getGoals();
        } else {
            for (Goals g: children) {
                if (!g.goalSatisfied(d).equals("")){
                    return d.getGoals();
                }
            }
        }

        return "";
    }
}

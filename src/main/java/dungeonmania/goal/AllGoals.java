package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;

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
        String check = "";

        for (Goals l: children) {
            if (l.toString().equals("And")) {
                check = "And";
            } else if (l.toString().equals("Or")) {
                check = "Or";
            }
        }

        System.out.println(check);

        if (check.equals("And")) {

//            for (EntityResponse e: d.getEntities()) {
//                if (!e.getType().equals("wall")) {
//                    System.out.println(e.getType());
//
//                }
//            }

            for (Goals g: children) {
                if (!g.goalSatisfied(d).equals("")){
                    return d.getGoals();
                }
            }

            return "";
        } else if (check.equals("Or")) {
            for (Goals g: children) {
                if (g.goalSatisfied(d).equals("")){
                    return "";
                }

            }

            return d.getGoals();
        } else {
            System.out.println("Herlro");
            for (Goals g: children) {
                if (!g.goalSatisfied(d).equals("")){
                    return d.getGoals();
                }
            }

            return "";
        }

    }
}

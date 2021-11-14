package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.List;

public class BoulderGoal implements Goals{
    public BoulderGoal() {
    }

    @Override
    public String goalSatisfied(DungeonResponse d) {
        List<Position> boulderPositions = new ArrayList<>();
        List<Position> switchPositions = new ArrayList<>();
        List<EntityResponse> listOfEntities = d.getEntities();

        for (EntityResponse e: listOfEntities) {
            if (e.getType().equals("boulders")) {
                boulderPositions.add(e.getPosition());
            } else if (e.getType().equals("switches")) {
                switchPositions.add(e.getPosition());
            }
        }

        int count = 0;
        for (Position p: boulderPositions) {
            for (Position p1: switchPositions) {
                if (p.equals(p1)) {
                    count = count + 1;
                }
            }
        }

        if (count == boulderPositions.size()) {
            return "";
        } else {
            return d.getGoals();
        }
    }
}

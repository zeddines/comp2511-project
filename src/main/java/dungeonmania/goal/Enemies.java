package dungeonmania.goal;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Enemies implements Goals{
    public Enemies() {
    }

    @Override
    public String goalSatisfied(DungeonResponse d) {
        // TODO: PLEASE FINISH
        for (EntityResponse e: d.getEntities()) {
            if (e.getType().equals("mercenary") || e.getType().equals("spider") || e.getType().equals("zombie_toast")) {
                return d.getGoals();
            }
        }

        return "";
    }
}

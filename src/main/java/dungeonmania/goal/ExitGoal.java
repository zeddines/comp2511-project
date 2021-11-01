package dungeonmania.goal;

import dungeonmania.entity.Entity;
import dungeonmania.entity.creature.Player;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

import java.util.List;

public class ExitGoal implements Goals{
    @Override
    public boolean goalSatisfied(DungeonResponse d) {
        List<EntityResponse> listOfEntities = d.getEntities();

        Position player = null;
        Position exit = null;

        for(EntityResponse e: listOfEntities) {
            if (e.getType().equals("player")) {
                player = new Position(e.getPosition().getX(), e.getPosition().getY());
            } else if (e.getType().equals("exit")) {
                exit = new Position(e.getPosition().getX(), e.getPosition().getY());
            }
        }

        if (player.equals(exit)) {
            return true;
        } else {
            return false;
        }
    }
}

package dungeonmania.entity.creature;

import dungeonmania.entity.interfaces.MovementNPC;
import dungeonmania.util.Position;

public class SpiderMovementNPC implements MovementNPC {
    private boolean direction;
    private int location;

    public SpiderMovementNPC() {
        direction = true;
        location = 0;
    }

    @Override
    public Position move(Position position) {

        return new Position(1,2);
    }
}

package dungeonmania.entity.creature;

import dungeonmania.entity.interfaces.MovementNPC;
import dungeonmania.util.Position;

public class ZombieToastMovementNPC implements MovementNPC{
    public ZombieToastMovementNPC() {
    }

    @Override
    public Position move(Position position) {
        return new Position(1,2);
    }
}

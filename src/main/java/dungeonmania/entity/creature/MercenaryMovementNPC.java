package dungeonmania.entity.creature;

import dungeonmania.entity.interfaces.MovementNPC;
import dungeonmania.util.Position;

public class MercenaryMovementNPC implements MovementNPC {
    public MercenaryMovementNPC() {
    }

    @Override
    public Position move(Position position) {
        return new Position(1,2);
    }
}

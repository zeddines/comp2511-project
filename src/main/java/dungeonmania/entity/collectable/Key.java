package dungeonmania.entity.collectable;

import dungeonmania.DungeonManiaController;
import dungeonmania.util.Position;

public class Key extends Collectable{

    public Key(DungeonManiaController game, String id, String type, Position position, boolean isInteractable) {
        super(game, id, type, position, isInteractable);
    }
}

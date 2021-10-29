package dungeonmania.entity.collectable;

import dungeonmania.DungeonManiaController;
import dungeonmania.entity.interfaces.Usable;
import dungeonmania.util.Position;

public class Bomb extends Collectable implements Usable{

    public Bomb(DungeonManiaController game, String id, String type, Position position, boolean isInteractable) {
        super(game, id, type, position, isInteractable);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        removeFromInventory();
    }
}

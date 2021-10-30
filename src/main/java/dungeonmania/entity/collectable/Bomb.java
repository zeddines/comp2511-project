package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.interfaces.Usable;
import dungeonmania.util.Position;

public class Bomb extends Collectable implements Usable{

    public Bomb(Position current, String type, DungeonMapAPI map) {
        super(map, type, current, false);
    }

    @Override
    public void use() {
        //deploy the bomb

        //remove it
        removeFromInventory();
    }
}

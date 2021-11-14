package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;

public class SunStone extends Collectable {

    public SunStone(Position position, String type, DungeonMapAPI map) {
        super(map, type, position);
    }    
}

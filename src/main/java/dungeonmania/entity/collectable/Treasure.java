package dungeonmania.entity.collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class Treasure extends Collectable{
    /**
     * can be collect ed by the character 
     */
    public Treasure(Position current, String type, DungeonMapAPI map) {
        super(map, type, current);
    }

    public Treasure(Creature owner, String type, DungeonMapAPI map) {
        super(map, type, owner);
    }
}

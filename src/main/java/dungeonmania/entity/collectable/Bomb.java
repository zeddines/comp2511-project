package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class Bomb extends Entity implements EntityAPI {
    boolean hasBeenSet; //you can only pick up a bomb once
    /**
     * can be collected by char
     * when char places a bomb cardinally adjacent to a switch
     * if boulder is pushed onto the switch then the bomb explodes
     * destroying all entieis in the bomb's blast radius except player 
     */
    public Bomb(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, false, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

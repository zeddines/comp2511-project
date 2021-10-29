package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class ZombieToast extends Entity {
    /**
     * Zombies spawn at zombie spawners and move in random directions 
     * Zombies are limited by the same movement constraints as character
     * except portals have no effect on then  
     */
    public ZombieToast(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, true, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

}

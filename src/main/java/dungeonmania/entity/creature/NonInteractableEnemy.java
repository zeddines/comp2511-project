package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class NonInteractableEnemy extends Enemy {
    /**
     * Zombies spawn at zombie spawners and move in random directions 
     * Zombies are limited by the same movement constraints as character
     * except portals have no effect on then  
     */
    public NonInteractableEnemy(Position current, String type, DungeonMapAPI map) {

        super(map, type, current);
    }
}

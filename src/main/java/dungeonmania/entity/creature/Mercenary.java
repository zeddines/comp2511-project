package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Mercenary extends Entity {
    /**
     * On maps with at least one enemy, mercenaries spawn at the entry location periodically
     * They constrantly move towarsd the character, stopping if they cannot move any closer 
     * Mercenaries are limited by same movement constraints as the character
     * All mecenaries are considered hostile, unless the character can bribe them with a certain amount of gold 
     * in which case they become allies 
     * As an ally, once it reaches the player it simply follows the player around 
     */
    public Mercenary(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, true, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

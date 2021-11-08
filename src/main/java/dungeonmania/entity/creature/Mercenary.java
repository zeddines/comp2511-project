package dungeonmania.entity.creature;
import dungeonmania.entity.interfaces.Interactable;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class Mercenary extends Enemy implements Interactable{
    /**
     * On maps with at least one enemy, mercenaries spawn at the entry location periodically
     * They constrantly move towarsd the character, stopping if they cannot move any closer 
     * Mercenaries are limited by same movement constraints as the character
     * All mecenaries are considered hostile, unless the character can bribe them with a certain amount of gold 
     * in which case they become allies 
     * As an ally, once it reaches the player it simply follows the player around 
     */

     // please set battleStat and movement as input to constructor( movment rn is null)
    public Mercenary(Position current, String type, DungeonMapAPI map) {
        super(map, type, current, null, new StandardBattleStat(10, 10, 0));
    }

    @Override
    public void interact() throws InvalidActionException{
        Player player = getGame().getPlayer();
        if (Position.calculateCardinalDistanceBetween(player.getPosition(), getPosition()) > 2)
            throw new InvalidActionException("not cardinally adjacent to the spawner");
        //TODO PAYING
    }

}

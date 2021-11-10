package dungeonmania.entity.creature;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class Assassin extends Enemy{

    /**
     * On maps with at least one enemy, assassins spawn at the entry location periodically
     * Technically mercenaries, but with less than 30% chance of spawning in the mercenary places
     * They constrantly move towarsd the character, stopping if they cannot move any closer 
     * Mercenaries are limited by same movement constraints as the character
     * All assassins are considered hostile, unless the character can bribe them with the one ring 
     * and the base amount of gold equal to how much mercenaries receive in which case they become allies 
     * As an ally, once it reaches the player it simply follows the player around 
     */


     // please set battleStat and movement as input to constructor( movment rn is null)
     public Assassin(Position current, String type, DungeonMapAPI map) {
        super(map, type, current, true, null, new StandardBattleStat(10, 10, 0));
    }
}

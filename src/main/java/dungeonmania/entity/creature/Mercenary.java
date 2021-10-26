package dungeonmania.entity.creature;

public class Mercenary extends Creature {
    /**
     * On maps with at least one enemy, mercenaries spawn at the entry location periodically
     * They constrantly move towarsd the character, stopping if they cannot move any closer 
     * Mercenaries are limited by same movement constraints as the character
     * All mecenaries are considered hostile, unless the character can bribe them with a certain amount of gold 
     * in which case they become allies 
     * As an ally, once it reaches the player it simply follows the player around 
     */
    public Mercenary() {
        super(); 
    }
}

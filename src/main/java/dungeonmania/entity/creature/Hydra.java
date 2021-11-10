package dungeonmania.entity.creature;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class Hydra extends Enemy{
    /**
     * Hydras spawn at random locations in the dungeon from the beginning of the game only in hard mode
     * When the hydras spawns, they only have the same movement constraints as Zombie Toasts     
     * When hydras are attacked, there is a 50% chance that the health will increase rather than decrease
     * Because two heads regrow once one is cut off
     */
    public Hydra(Position current, String type, DungeonMapAPI map) {
        //missing movement strategy and battleStat as inputs
        //BattleStat can definitely be an argumnet for constructor, (movment should be too, but rn it is null)
        super(map, type, current, false, null, new StandardBattleStat(5, 5, 0));
    }
}

package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;
import dungeonmania.util.*;

public class Spider extends Enemy {
    /**
     * Spiders spawn at random locations in the dungeon from the beginning of the game 
     * When the spider spawns, they immediately move the 1 square upwards (towards top of screen)
     * then begin circling their spawn point
     * Spiders are able to traverse through walls, doors, switches, portals, exits (no effect) 
     * Not able to traverse through boulders in which case it will reverse direction 
     * At least 4 spiders at a time 
     */
    public Spider(Position current, String type, DungeonMapAPI map) {
        //missing movement strategy and battleStat as inputs
        //BattleStat can definitely be an argumnet for constructor, (movment should be too, but rn it is null)
        super(map, type, current, null, new StandardBattleStat(5, 5, 0));
    }
}

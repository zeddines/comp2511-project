package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class ZombieToastSpawner extends Entity {
    /**
     *  Spawns zombie toasts every 20 ticks in an open square cardinally adjacent to the spawner 
     *  Character can destroy a zombie spawner if they have a weapon and are cardinally adjacent 
     *  to spawner 
     */
    public ZombieToastSpawner(Position current, String type, DungeonMapAPI map) {
        super(current, type, true, true, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
}

package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class ZombieToastSpawner extends Entity {
    public ZombieToastSpawner(Position currentPosition) {
        super(currentPosition); 
    }

    /**
     *  Spawns zombie toasts every 20 ticks in an open square cardinally adjacent to the spawner 
     *  Character can destroy a zombie spawner if they have a weapon and are cardinally adjacent 
     *  to spawner 
     */
    @Override
    
public boolean action(EntityAPI creature) {
    return false;
}
}

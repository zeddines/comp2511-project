package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.RegularActionEntity;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class ZombieToastSpawner extends Entity implements RegularActionEntity{
    private int coolDown;
    private int roundsLeft;
    /**
     *  Spawns zombie toasts every 20 ticks in an open square cardinally adjacent to the spawner 
     *  Character can destroy a zombie spawner if they have a weapon and are cardinally adjacent 
     *  to spawner 
     */
    //can input coolDown as argument constructor, can be done in factory
    public ZombieToastSpawner(Position current, String type, DungeonMapAPI map) {
        super(map, current, type, true);
        this.coolDown = 20;
        this.roundsLeft = coolDown;
    }

    @Override
    public void regularAction() {
        // TODO
        //minus roundsLeft, if 0 then spawn zombie and reset
    }
}

package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.RegularActionEntity;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class MercenarySpawner extends Entity implements RegularActionEntity {
    private int coolDown;
    private int roundsLeft;
    /**
     *  Spawns mercenaries randomly in an open square cardinally adjacent to the spawner      
     */
    //can input coolDown as argument constructor, can be done in factory
    public MercenarySpawner(Position current, String type, DungeonMapAPI map) {
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
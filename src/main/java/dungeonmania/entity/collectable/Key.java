package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Key extends Collectable{
    private int key; 

    /**
     * can be picked up by the player when they move into the square containing it
     * character can only carry one key at a time, and only one door has a lock that 
     * fits that key
     * the key also disappears once it is used to open its corresponding door  
     */
    public Key(Position current, String type, int key,DungeonMapAPI map) {
        super(map, type, current);
        this.key = key;
    }

    public int getKey(){
        return key;
    }

    @Override
    public void collideAction(Player player){
        for (Collectable collectable : player.getNonBattleItems()){
            if (collectable instanceof Key)
                return;
        }
        super.collideAction(player);
    }
}

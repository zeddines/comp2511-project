package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Key;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Door extends Entity {
    private int key; 

    /**
     *  Exists in conjunction with a single key that can open it 
     *  If character holds the key, they can open the door by moving through it
     *  Remains open once opened 
     */
    
    public Door(Position current, String type, int key, DungeonMapAPI map) {
        super(map, current, type);
        this.key = key;
    }

    ///TODO MODIFY WHEN IMPLEMENTING SUNSTONE
    public boolean canUnlock(List<Collectable> nonBattleItems){
        for (Collectable item : nonBattleItems){
            if (item instanceof Key && ((Key)item).getKey() == key){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canBeOnSamePosition(Enemy enemy){
        if ("spider".equals(enemy.getType()))
            return true;
        return false;
    }

    @Override
    public boolean canBeOnSamePosition(Boulder boulder){
        return false;
    }

    public void unlock(List<Collectable> nonBattleItems){
        for (Collectable collectable : DungeonMap.shallowClone(nonBattleItems)){
            if (collectable instanceof Key){
                Key keyObj = ((Key)collectable);
                if (keyObj.getKey() == key){
                    nonBattleItems.remove(keyObj);
                    getGame().removeEntity(this);
                }
            }
        }       
    }
}

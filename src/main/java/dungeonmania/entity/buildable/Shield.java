package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;
import dungeonmania.map.DungeonMapAPI;

public class Shield extends Collectable implements Guard{
    private int durability; 

    /**
     * crafted with 2 wood and 1 treasure or key 
     * shields decrease the effect of enemy attack 
     * each shield has specific durability that dictates the number of times it can be used 
     * before it deteriorates 
     */

    //TODO shield doesn't have a postion since they are buildables, rn the durability is hard coded, need to change factory
    public Shield(String type, DungeonMapAPI map, Creature owner) {
        super(map, type, false, owner);
        this.durability = 4;
    }

    @Override
    public void modifyDefense(BattleStat battleStat) {
        battleStat.multiplyDefense(4);
        durability--;
        if (durability == 0){
            getOwner().removeCollectable(this);
        }
    }
}

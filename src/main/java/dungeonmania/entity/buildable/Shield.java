package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMapAPI;

public class Shield extends Collectable implements BattleGear{
    private int durability; 

    /**
     * crafted with 2 wood and 1 treasure or key 
     * shields decrease the effect of enemy attack 
     * each shield has specific durability that dictates the number of times it can be used 
     * before it deteriorates 
     */

    public Shield(String type, DungeonMapAPI map, Creature owner, int durability) {
        super(map, type, owner);
        this.durability = durability;
    }

    @Override
    public void modifyStates(BattleStat battleStat) {
        battleStat.multiplyDefense(4);
    }

    @Override
    public int getDurability() {
        return durability;
    } 

    @Override
    public void reduceDurability(){
        durability--;
    }

    @Override
    public boolean isWeapon() {
        return false;
    }

    @Override
    public boolean isDefense() {
        return true;
    }
}

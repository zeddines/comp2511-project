package dungeonmania.entity.buildable;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Player;
import dungeonmania.util.*;
import org.json.JSONObject;

import dungeonmania.entity.interfaces.*;
import dungeonmania.map.DungeonMapAPI;

public class Bow extends Collectable implements Weapon{
    public int durability; 
    
    /**
     * crafted with 1 wood and 3 arrows 
     * allows char to attack twice in a single round 
     */
    public Bow(String type, DungeonMapAPI map, Creature owner) {
        super(map, type, false, owner);
        this.durability = 4;
    }
    
    @Override
    public void modifyAttack(BattleStat battleStat) {
        battleStat.multiplyAttack(2);
        durability --;
    }

    @Override
    public int getDurability() {
        return durability;
    } 
}

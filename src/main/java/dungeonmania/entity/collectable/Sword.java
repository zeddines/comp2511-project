package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.util.Position;

public class Sword extends Collectable implements BattleGear{
    private int durability;

    //constructor for sword with an owner
    //TODO durability should be passed from the factory, rn it is hard coded
    public Sword(String type, DungeonMapAPI map, Creature owner) {
        super(map, type, owner);
        this.durability = 5;
    }


    //constructor for sword on ground, rn the factory is using this one
    public Sword(Position current, String type, DungeonMapAPI map) {
        super(map, type, current);
        this.durability = 1;
    }
    
    @Override
    public void modifyStates(BattleStat battleStat) {
        battleStat.addFlatAttack(2);
    } 

    @Override
    public int getDurability() {
        return durability;
    }


    @Override
    public void reduceDurability() {
        durability --;
    }


    @Override
    public boolean isWeapon() {
        return true;
    }


    @Override
    public boolean isDefense() {
        return false;
    } 
}

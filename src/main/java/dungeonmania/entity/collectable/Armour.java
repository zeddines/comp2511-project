package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.util.Position;


public class Armour extends Collectable implements BattleGear{
    private int durability;

    //constructor for armour with an owner
    //rn durability is hard coded, should pass in as constructor argument
    public Armour(String type, DungeonMapAPI map, Creature owner, int durability) {
        super(map, type, owner);
        this.durability = durability;
    }


    //constructor for armour on ground
    public Armour(Position current, String type, DungeonMapAPI map, int durability) {
        super(map, type, current);
        this.durability = durability;
    }

    @Override
    public int getDurability() {
        return durability;
    }


    @Override
    public void modifyStates(BattleStat battleStat) {
        battleStat.multiplyDefense(2);
    }


    @Override
    public void reduceDurability() {
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

package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMapAPI;

public class Anduril extends Collectable implements BattleGear{
    private int durability;

    public Anduril(String type, DungeonMapAPI map, Creature owner) {
        super(map, type, owner);
        this.durability = 5;
    }

    @Override
    public void modifyStates(BattleStat battleStat) {
        battleStat.addFlatAttack(4);
    }

    @Override
    public void reduceDurability() {
        durability--;
    }

    @Override
    public int getDurability() {
        return durability;
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

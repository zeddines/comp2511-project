package dungeonmania.entity.buildable;

import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMapAPI;

public class MidnightArmour extends Collectable implements BattleGear{
    private int durability; 

    public MidnightArmour(DungeonMapAPI game, String type, Creature owner, int durability) {
        super(game, type, owner);
        this.durability = durability;
    }

    @Override
    public void modifyStates(BattleStat battleStat) {
        battleStat.addFlatAttack(5);
        battleStat.addFlatDefense(4);
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
        return true;
    }
    
}

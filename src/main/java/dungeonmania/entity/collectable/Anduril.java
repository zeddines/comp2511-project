package dungeonmania.entity.collectable;

import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;

public class Anduril extends Collectable implements BattleGear{
    private int durability;

    public Anduril(Position position, String type, DungeonMapAPI map) {
        super(map, type, position);
        this.durability = 3;
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

package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;

public class Anduril extends Collectable implements BattleGear{
    private int durability;

    public Anduril(Position position, String type, DungeonMapAPI map, int durability) {
        super(map, type, position);
        this.durability = durability;
    }

    

    public Anduril(Creature owner, DungeonMapAPI game, String type, int durability) {
        super(game, type, owner);
        this.durability = durability;
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

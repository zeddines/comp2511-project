package dungeonmania.entity.buildable;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMapAPI;

public class Bow extends Collectable implements BattleGear{
    private int durability; 
    
    /**
     * crafted with 1 wood and 3 arrows 
     * allows char to attack twice in a single round 
     */
    public Bow(String type, DungeonMapAPI map, Creature owner, int durability) {
        super(map, type, owner);
        this.durability = durability;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void modifyStates(BattleStat battleStat) {
        battleStat.multiplyAttack(2);
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

package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.Weapon;
import dungeonmania.util.Position;

public class Sword extends Collectable implements Weapon{
    private int durability;

    //constructor for sword with an owner
    //TODO durability should be passed from the factory, rn it is hard coded
    public Sword(String type, DungeonMapAPI map, Creature owner) {
        super(map, type, false, owner);
        this.durability = 5;
    }


    //constructor for sword on ground, rn the factory is using this one
    public Sword(Position current, String type, DungeonMapAPI map) {
        super(map, type, current, false);
        this.durability = 5;
    }
    
    @Override
    public void modifyAttack(BattleStat battleStat) {
        battleStat.addFlatAttack(2);
        durability --;
        if (durability == 0){
            getOwner().removeCollectable(this);
        }
    } 
}

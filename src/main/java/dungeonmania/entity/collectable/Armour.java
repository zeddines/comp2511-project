package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.Guard;
import dungeonmania.util.Position;


public class Armour extends Collectable implements Guard{
    private int durability;

    //constructor for armour with an owner
    //rn durability is hard coded, should pass in as constructor argument
    public Armour(String type, DungeonMapAPI map, Creature owner) {
        super(map, type, false, owner);
        this.durability = 5;
    }


    //constructor for armour on ground
    public Armour(Position current, String type, DungeonMapAPI map) {
        super(map, type, current, false);
        this.durability = 5;
    }

    //TODO NOT MENTIONED (REMOVED OVERRIDING COLLIDEACTION)

    @Override
    public void modifyDefense(BattleStat battleStat) {
        battleStat.multiplyDefense(2);
        durability--;
        if (durability == 0){
            getOwner().removeCollectable(this);
        }
    }
}

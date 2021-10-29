package dungeonmania.entity.collectable;

import dungeonmania.DungeonManiaController;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.Guard;
import dungeonmania.util.Position;


public class Armour extends Collectable implements Guard{
    private int durability;

    //constructor for armour with an owner
    public Armour(DungeonManiaController game, String id, String type, boolean isInteractable, Creature owner, int durability) {
        super(game, id, type, isInteractable, owner);
        this.durability = durability;
    }

    //constructor for armour on ground
    public Armour(DungeonManiaController game, String id, String type, Position position, boolean isInteractable, int durability) {
        super(game, id, type, position, isInteractable);
        this.durability = durability;
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

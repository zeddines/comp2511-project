package dungeonmania.collectables;

import dungeonmania.BattleStat;
import dungeonmania.Collectable;
import dungeonmania.Creature;
import dungeonmania.DungeonManiaController;
import dungeonmania.Weapon;
import dungeonmania.util.Position;

public class Sword extends Collectable implements Weapon{
    private int durability;

    //constructor for sword with an owner
    public Sword(DungeonManiaController game, String id, String type, boolean isInteractable, Creature owner, int durability) {
        super(game, id, type, isInteractable, owner);
        this.durability = durability;
    }

    //constructor for sword on ground
    public Sword(DungeonManiaController game, String id, String type, Position position, boolean isInteractable, int durability) {
        super(game, id, type, position, isInteractable);
        this.durability = durability;
    }

    //TODO NOT MENTIONED (REMOVED OVERRIDING COLLIDEACTION)
    
    @Override
    public void modifyAttack(BattleStat battleStat) {
        battleStat.addFlatAttack(2);
        durability --;
        if (durability == 0){
            getOwner().removeCollectable(this);
        }
    } 
}

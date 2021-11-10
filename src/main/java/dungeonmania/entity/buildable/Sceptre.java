package dungeonmania.entity.buildable;

import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.MindControlEffect;
import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;

public class Sceptre extends Collectable{
    private int durability;

    public Sceptre(DungeonMapAPI game, String type, Creature owner) {
        super(game, type, owner);
        this.durability = 3;
    }

    public void mindControl(Creature target){
        (new MindControlEffect(target, 10, game)).apply();
        durability--;
        if (durability == 0)
            getOwner().removeCollectable(this);
    }
}

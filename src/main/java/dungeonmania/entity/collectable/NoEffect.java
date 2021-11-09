package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;

public class NoEffect extends Effect{
    public NoEffect(Creature target, int lastFor, DungeonMapAPI game) {
        super(target, game);
    }

    @Override
    public void applyEffect() {
        return;
    }

    @Override
    public void endEffect() {
        return;
    }
}

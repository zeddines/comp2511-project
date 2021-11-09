package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;

public class NoEffect extends Effect{
    public NoEffect(int lastFor, DungeonMapAPI game) {
        super(game);
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

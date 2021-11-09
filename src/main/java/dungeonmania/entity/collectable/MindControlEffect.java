package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;

public class MindControlEffect extends Effect{

    public MindControlEffect(Creature target, int lastFor, DungeonMapAPI game) {
        super(target, lastFor, game);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void applyEffect() {
        getGame().addToAlly(getTarget());
    }

    @Override
    public void endEffect() {
        getGame().removeFromAlly(getTarget());
    }
}

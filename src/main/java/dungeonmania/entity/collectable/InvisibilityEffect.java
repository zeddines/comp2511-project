package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;

public class InvisibilityEffect extends Effect{

    public InvisibilityEffect(Creature target, int lastFor, DungeonMapAPI game){
        super(target, lastFor, game);
    }

    @Override
    public void applyEffect() {
        getTarget().applyInvisibleBuff();
        //TODO set all enemy random movement behaviour

    }

    @Override
    public void endEffect() {
        getTarget().revertInvisibleBuff();
        //set all enemy back to default movement behaviour

    }
}

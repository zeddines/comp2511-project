package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;

public class InvisibilityEffect extends Effect{

    public InvisibilityEffect(int lastFor, DungeonMapAPI game){
        super(lastFor, game);
    }

    @Override
    public void applyEffect() {
        getPlayer().applyInvisibleBuff();
        //TODO set all enemy random movement behaviour

    }

    @Override
    public void endEffect() {
        getPlayer().revertInvisibleBuff();
        //set all enemy back to default movement behaviour

    }
}

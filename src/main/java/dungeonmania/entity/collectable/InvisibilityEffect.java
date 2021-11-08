package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

public class InvisibilityEffect extends Effect{

    public InvisibilityEffect(int lastFor){
        super(lastFor);
    }

    @Override
    public void applyPotionEffect() {
        getOwner().setInvisible(true);
        //TODO set all enemy random movement behaviour

    }
}

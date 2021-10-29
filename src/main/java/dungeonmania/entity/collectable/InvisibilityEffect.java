package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

public class InvisibilityEffect extends PotionEffect{

    public InvisibilityEffect(int lastFor){
        super(lastFor);
    }

    @Override
    public String getType() {
        return "invisibility_potion";
    }

    @Override
    public void applyPotionEffect() {
        getOwner().setInvisible(true);
        //TODO set all enemy random movement behaviour

    }
}

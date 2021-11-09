package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

public class NoEffect extends PotionEffect{

    public NoEffect(){
        super(0);
    }

    @Override
    public String getType() {
        return "no_effect";
    }

    @Override
    public void applyPotionEffect() {
        return;
        //TODO set all enemy random movement behaviour

    }
    
}

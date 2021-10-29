package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

public class InvincibilityEffect extends PotionEffect{
    
    public InvincibilityEffect(int lastFor) {
        super(lastFor);
    }

    @Override
    public void applyPotionEffect() {
        getOwner().setInvincible(true);
        //TODO SET ALL ENEMIES FLEEING MOVEMENT BEHAVIOUR

    }

    @Override
    public String getType() {
        return "invincibility_potion";
    }

}

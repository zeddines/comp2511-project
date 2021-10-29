package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.PotionEffect;

public class InvincibilityEffect implements PotionEffect{
    private Potion potion;
    private Player player;
    private int effectLastFor;

    public InvincibilityEffect(Potion potion, Player player, int lastFor){
        this.potion = potion;
        this.player = player;
        this.effectLastFor = lastFor;
    }

    @Override
    public String getType() {
        return "invincibility_potion";
    }

    @Override
    public void applyPotionEffect() {
        player.setInvincible(true);
        //TODO SET ALL ENEMIES FLEEING MOVEMENT BEHAVIOUR
    }

    @Override
    public void updateEffectDuration() {
        effectLastFor--;
        if (effectLastFor == 0){
            player.removePotionEffect(potion);
        }
    }
}

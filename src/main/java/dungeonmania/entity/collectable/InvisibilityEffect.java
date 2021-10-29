package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.PotionEffect;

public class InvisibilityEffect implements PotionEffect{
    private Potion potion;
    private Player player;
    private int effectLastFor;

    public InvisibilityEffect(Potion potion, Player player, int lastFor){
        this.potion = potion;
        this.player = player;
        this.effectLastFor = lastFor;
    }

    @Override
    public String getType() {
        return "invisibility_potion";
    }

    @Override
    public void applyPotionEffect() {
        player.setInvisible(true);
        //TODO CAN SET ALL ENEMY TO RANDOM MOVEMENT BEHAVIOUR assumption
    }

    @Override
    public void updateEffectDuration() {
        effectLastFor--;
        if (effectLastFor == 0){
            player.removePotionEffect(potion);
        }
    }
}

package dungeonmania.collectables;

import dungeonmania.creatures.Player;

public class RecoverHealthEffect implements PotionEffect{
    private Potion potion;
    private Player player;
    private int effectLastFor;

    public RecoverHealthEffect(Potion potion, Player player){
        this.potion = potion;
        this.player = player;
        this.effectLastFor = 1;
    }

    @Override
    public String getType() {
        return "health_potion";
    }

    @Override
    public void applyPotionEffect() {
        player.getBattleStat().recoverToFull();
    }

    @Override
    public void updateEffectDuration() {
        effectLastFor--;
        if (effectLastFor == 0){
            player.removePotionEffect(potion);
        }
    }    
}

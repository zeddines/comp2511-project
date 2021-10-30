package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

abstract public class PotionEffect {
    private int effectLastFor;
    private Player player;
    private Potion potion;

    public PotionEffect(int lastFor){
        this.effectLastFor = lastFor;
    }
    
    public void updateEffectDuration() {
        effectLastFor--;
        if (effectLastFor == 0){
            player.removePotionEffect(potion);
        }
    }

    public void setOwner(Player player){
        this.player = player;
    }

    public Player getOwner(){
        return player;
    }

    public void setPotion(Potion potion){
        this.potion = potion;
    }

    abstract public String getType();
    abstract public void applyPotionEffect();
}

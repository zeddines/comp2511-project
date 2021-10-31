package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

abstract public class PotionEffect {
    private int durationLeft;
    private Player player;

    public PotionEffect(int lastFor){
        this.durationLeft = lastFor;
    }
    
    public void updateEffectDuration() {
        durationLeft--;
    }

    public void setOwner(Player player){
        this.player = player;
    }

    public Player getOwner(){
        return player;
    }

    public int getDurationLeft(){
        return durationLeft;
    }

    abstract public String getType();
    abstract public void applyPotionEffect();
}

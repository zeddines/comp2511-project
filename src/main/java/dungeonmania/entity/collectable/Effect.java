package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

abstract public class Effect {
    private int durationLeft;
    private Player player;

    public Effect(int lastFor){
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

    //abstract public String getType();
    abstract public void applyPotionEffect();
}

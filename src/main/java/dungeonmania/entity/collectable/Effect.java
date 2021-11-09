package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;

abstract public class Effect {
    private int effectDuration;
    private int durationLeft;
    private DungeonMapAPI game;

    public Effect(int lastFor, DungeonMapAPI game){
        this.effectDuration = lastFor;
        this.durationLeft = lastFor;
        this.game = game;
    }

    public Effect(DungeonMapAPI game){
        this.effectDuration = 0;
        this.durationLeft = 0;
        this.game = game;
    }
    
    public void updateEffectDuration() {
        durationLeft--;
        if (durationLeft == 0){
            endEffect();
            game.getPlayer().removeEffectInAction(this);
        }
    }

    public void apply(){
        if (effectDuration > 0)
            game.getPlayer().addEffectInAction(this);
        applyEffect();
    }

    
    abstract public void applyEffect();
    abstract public void endEffect();
    

    //getter 
    public DungeonMapAPI getGame() {
        return game;
    }

    public Player getPlayer(){
        return game.getPlayer();
    }
}

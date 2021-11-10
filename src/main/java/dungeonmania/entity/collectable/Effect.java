package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;

abstract public class Effect {
    private Creature target;
    private int effectDuration;
    private int durationLeft;
    private DungeonMapAPI game;

    public Effect(Creature target, int lastFor, DungeonMapAPI game){
        this.target = target;
        this.effectDuration = lastFor;
        this.durationLeft = lastFor;
        this.game = game;
    }

    public Effect(Creature target, DungeonMapAPI game){
        this(target, 0, game);
    }
    
    public void updateEffectDuration() {
        durationLeft--;
        if (durationLeft == 0){
            endEffect();
            game.removeEffectInAction(this);
        }
    }

    public void apply(){
        if (effectDuration > 0)
            game.addEffectInAction(this);
        applyEffect();
    }

    
    abstract public void applyEffect();
    abstract public void endEffect();
    

    //getter 
    public DungeonMapAPI getGame() {
        return game;
    }

    public Creature getTarget(){
        return target;
    }

    public void setTarget(Creature target){
        this.target = target;
    }
}

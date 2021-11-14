package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;

public class RecoverHealthEffect extends Effect{
    public RecoverHealthEffect(Creature target, DungeonMapAPI game){
        super(target, game);
    }
    
    @Override
    public void applyEffect() {
        getTarget().getBattleStat().recoverToFull();
    }

    @Override
    public void endEffect() {
        return;
    }  
}

package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;

public class RecoverHealthEffect extends Effect{
    public RecoverHealthEffect(DungeonMapAPI game){
        super(game);
    }
    
    @Override
    public void applyEffect() {
        getPlayer().getBattleStat().recoverToFull();
    }

    @Override
    public void endEffect() {
        return;
    }  
}

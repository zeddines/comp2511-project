package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;

public class RecoverHealthEffect extends PotionEffect{
    public RecoverHealthEffect(){
        super(1);
    }

    @Override
    public String getType() {
        return "health_potion";
    }

    @Override
    public void applyPotionEffect() {
        getOwner().getBattleStat().recoverToFull();
    }  
}

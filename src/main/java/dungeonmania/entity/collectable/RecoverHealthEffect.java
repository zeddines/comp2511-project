package dungeonmania.entity.collectable;

public class RecoverHealthEffect extends Effect{
    public RecoverHealthEffect(){
        super(1);
    }
    
    @Override
    public void applyPotionEffect() {
        getOwner().getBattleStat().recoverToFull();
    }  
}

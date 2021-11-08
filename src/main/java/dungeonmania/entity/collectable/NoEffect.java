package dungeonmania.entity.collectable;

public class NoEffect extends Effect{
    public NoEffect(int lastFor) {
        super(lastFor);
    }

    @Override
    public void applyPotionEffect() {
        return;
    }
}

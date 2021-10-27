package dungeonmania.collectables;


public interface PotionEffect {
    public String getType();
    public void applyPotionEffect();
    public void updateEffectDuration();
}
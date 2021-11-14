package dungeonmania.entity.interfaces;

public interface BattleGear {
    public void modifyStates(BattleStat battleStat);
    public void reduceDurability();
    public int getDurability();
    public boolean isWeapon();
    public boolean isDefense();
}

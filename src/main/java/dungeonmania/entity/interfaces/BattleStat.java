package dungeonmania.entity.interfaces;

import java.util.ArrayList;

public interface BattleStat {
    public int getAttack();
    public int getReducedAttack(int damage);
    public int getHealth();
    public void recoverToFull();
    public void reduceHealth(int health);
    public void addBattleGear(BattleGear newItem);
    public ArrayList<BattleGear> getBattleGears();
    public void reduceAllDurability();
    public void removeAllDeteriorated();

    //modifiers
    public void addFlatAttack(int flatAttack);
    public void addFlatDefense(int flatDefense);
    public void multiplyAttack(int attackMultipliedBy);
    public void multiplyDefense(int defenseMultipliedBy);
}

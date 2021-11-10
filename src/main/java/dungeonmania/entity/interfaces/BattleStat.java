package dungeonmania.entity.interfaces;

import java.util.ArrayList;

import dungeonmania.entity.creature.Creature;

public interface BattleStat {
    public double getAttack();
    public double getReducedAttack(double damage);
    public double getHealth();
    public void recover(double health);
    public void recoverToFull();
    public void reduceHealth(double damageReceived, Creature enemy);
    public void addBattleGear(BattleGear newItem);
    public ArrayList<BattleGear> getBattleGears();
    public void reduceAllDurability();
    public void removeAllDeteriorated();
    public Creature getOwner();
    public boolean hasBossSlayer();

    //modifiers
    public void addFlatAttack(double flatAttack);
    public void addFlatDefense(double flatDefense);
    public void multiplyAttack(double attackMultipliedBy);
    public void multiplyDefense(double defenseMultipliedBy);
}

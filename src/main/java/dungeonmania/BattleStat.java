package dungeonmania;

import java.util.ArrayList;

public interface BattleStat {
    public int getAttack();
    //TODO NOT MENTIONED UML(changed name and all modifier methods)
    public int getReducedAttack(int damage);
    public int getHealth();
    public void recoverToFull();
    public void reduceHealth(int health);
    public void addWeapon(Weapon weapon);
    public void addGuard(Guard guard);
    public void removeWeapon(Weapon weapon);
    public void removeGuard(Guard guard);
    public ArrayList<Weapon> getWeapons();
    public ArrayList<Guard> getGuards();

    //modifiers
    public void addFlatAttack(int flatAttack);
    public void addFlatDefense(int flatDefense);
    public void multiplyAttack(int attackMultipliedBy);
    public void multiplyDefense(int defenseMultipliedBy);
}

package dungeonmania.entity.interfaces;

import java.util.ArrayList;

public interface BattleStat {
    public int getAttack();
    //TODO NOT MENTIONED UML(changed name and all modifier methods)
    public int getReducedAttack(int damage);
    public int getHealth();
    public void recoverToFull();
    public void reduceHealth(int health);
    public void addWeapon(Weapon newItem);
    public void addGuard(Guard guard);
    public ArrayList<Weapon> getWeapons();
    public ArrayList<Guard> getGuards();

    //modifiers
    public void addFlatAttack(int flatAttack);
    public void addFlatDefense(int flatDefense);
    public void multiplyAttack(int attackMultipliedBy);
    public void multiplyDefense(int defenseMultipliedBy);
}

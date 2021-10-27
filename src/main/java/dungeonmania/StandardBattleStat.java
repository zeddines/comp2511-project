package dungeonmania;

import java.util.ArrayList;

public class StandardBattleStat implements BattleStat{
    private int maxHealth;
    private int currentHealth;
    private int baseAttack;
    private int baseDefense;
    private ArrayList<Weapon> weapons;
    private ArrayList<Guard> guards;

    //modifiers
    private int flatAttackIncrease = 0;
    private int flatDefenseIncrease = 0;
    private int multiplyAttackIncrease = 1;
    private int multiplayDefenseIncrease = 1;

    public StandardBattleStat(int maxHealth, int attack, int defense){
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.baseAttack = attack;
        this.baseDefense = defense;
        weapons = new ArrayList<>();
        guards = new ArrayList<>();
    }

    //initialize with weapons and guards
    public StandardBattleStat(int maxHealth, int attack, int defense, ArrayList<Weapon> weapons, ArrayList<Guard> guards){
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.baseAttack = attack;
        this.baseDefense = defense;
        this.weapons = weapons;
        this.guards = guards;
    }

    @Override
    public int getAttack() {
        int totalAttack;
        for (Weapon weapon : weapons){
            weapon.modifyAttack(this);
        }
        totalAttack = (baseAttack + flatAttackIncrease) * multiplyAttackIncrease; 
        resetModifiers();
        return totalAttack;
    }

    @Override
    public int getReducedAttack(int damage) {
        int reducedDamage;
        for (Guard guard : guards){
            guard.modifyDefense(this);
        } 
        reducedDamage = (damage - baseDefense - flatDefenseIncrease) / multiplayDefenseIncrease;
        resetModifiers();
        return reducedDamage;        
    }
    
    //TODO NOT MENTIONED IN UML (resets and modifiers methods)
    private void resetModifiers(){
        flatAttackIncrease = 0;
        flatDefenseIncrease = 0;
        multiplyAttackIncrease = 1;
        multiplayDefenseIncrease = 1;
    }
    
    @Override
    public void addFlatAttack(int flatAttack) {
        flatAttackIncrease += flatAttack; 
    }

    @Override
    public void addFlatDefense(int flatDefense) {
        flatDefenseIncrease += flatDefense;
    }

    @Override
    public void multiplyAttack(int attackMultipliedBy) {
        multiplyAttackIncrease *= attackMultipliedBy;
    }

    @Override
    public void multiplyDefense(int defenseMultipliedBy) {
        multiplayDefenseIncrease *= defenseMultipliedBy;
    }

    @Override
    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    @Override 
    public void addGuard(Guard guard){
        guards.add(guard);
    }    
    
    @Override
    public void removeWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    @Override
    public void removeGuard(Guard guard) {
        guards.remove(guard);
    }

    //getter setter
    @Override
    public int getHealth() {
        return currentHealth;
    }

    @Override
    public void reduceHealth(int health){
        this.currentHealth -= health;
        //TODO NOT MENTIONED IN UML AND CHECK REVIVABLE ITEMS

    }

    @Override
    public void recoverToFull(){
        currentHealth = maxHealth;
    }

    @Override
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public ArrayList<Guard> getGuards() {
        return guards;
    }

}

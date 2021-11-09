package dungeonmania.entity.creature;

import java.util.ArrayList;
import java.util.Iterator;

import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMap;
import dungeonmania.entity.collectable.Anduril;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Ring;
import dungeonmania.entity.interfaces.BattleGear;


public class StandardBattleStat implements BattleStat{
    private Creature owner;

    private double maxHealth;
    private double currentHealth;
    private double baseAttack;
    private double baseDefense;
    private ArrayList<BattleGear> battleGears;

    //modifiers
    private double flatAttackIncrease = 0;
    private double flatDefenseIncrease = 0;
    private double multiplyAttackIncrease = 1;
    private double multiplayDefenseIncrease = 1;

    public StandardBattleStat(Creature owner, double maxHealth, double attack, double defense){
        this.owner = owner;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.baseAttack = attack;
        this.baseDefense = defense;
        battleGears = new ArrayList<>();
    }

    //initialize with weapons and guards
    public StandardBattleStat(Creature owner, double maxHealth, double attack, double defense, ArrayList<BattleGear> battleGears){
        this(owner, maxHealth, attack, defense);
        this.battleGears = battleGears;
    }

    @Override
    public double getAttack() {
        double totalAttack;
        for (BattleGear battleGear : battleGears){
            battleGear.modifyStates(this);
        }

        totalAttack = (baseAttack + flatAttackIncrease) * multiplyAttackIncrease; 
        resetModifiers();
        return totalAttack >= 0 ? totalAttack : 0;
    }

    @Override
    public double getReducedAttack(double damage) {
        double reducedDamage;
        for (BattleGear battleGear : battleGears){
            battleGear.modifyStates(this);
        } 
        reducedDamage = (damage - baseDefense - flatDefenseIncrease) / multiplayDefenseIncrease;
        resetModifiers();
        return reducedDamage >= 0 ? reducedDamage : 0;        
    }

    @Override
    public void reduceAllDurability(){
        for (BattleGear battleGear : battleGears){
            battleGear.reduceDurability();
        }
    }

    @Override
    public void removeAllDeteriorated(){
        for (BattleGear battleGear : DungeonMap.shallowClone(battleGears)){
            if (battleGear.getDurability() == 0)
                battleGears.remove(battleGear);
        }
    }
    
    //TODO NOT MENTIONED IN UML (resets and modifiers methods)
    private void resetModifiers(){
        flatAttackIncrease = 0;
        flatDefenseIncrease = 0;
        multiplyAttackIncrease = 1;
        multiplayDefenseIncrease = 1;
    }
    
    @Override
    public void addFlatAttack(double flatAttack) {
        flatAttackIncrease += flatAttack; 
    }

    @Override
    public void addFlatDefense(double flatDefense) {
        flatDefenseIncrease += flatDefense;
    }

    @Override
    public void multiplyAttack(double attackMultipliedBy) {
        multiplyAttackIncrease *= attackMultipliedBy;
    }

    @Override
    public void multiplyDefense(double defenseMultipliedBy) {
        multiplayDefenseIncrease *= defenseMultipliedBy;
    }

    @Override 
    public void addBattleGear(BattleGear battleGear){
        battleGears.add(battleGear);
    }    

    //getter setter
    @Override
    public double getHealth() {
        return currentHealth;
    }

    @Override
    public void reduceHealth(double damageReceived, Creature enemy){
        currentHealth = (currentHealth - damageReceived) > 0 ? currentHealth - damageReceived :  0;
    }

    @Override
    public void recover(double health){
        currentHealth = (currentHealth + health) <= maxHealth ? currentHealth + health : maxHealth;
    }

    @Override
    public void recoverToFull(){
        currentHealth = maxHealth;
    }

    @Override
    public ArrayList<BattleGear> getBattleGears() {
        return battleGears;
    }

    @Override
    public Creature getOwner(){
        return owner;
    }

    @Override
    public boolean hasBossSlayer(){
        for (BattleGear battleGear : battleGears){
            if (battleGear instanceof Anduril)
                return true;
        }
        return false;
    }
}

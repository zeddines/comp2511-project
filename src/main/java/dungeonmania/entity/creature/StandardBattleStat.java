package dungeonmania.entity.creature;

import java.util.ArrayList;
import java.util.Iterator;

import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.map.DungeonMap;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Ring;
import dungeonmania.entity.interfaces.BattleGear;


public class StandardBattleStat implements BattleStat{
    private Creature owner;

    private int maxHealth;
    private int currentHealth;
    private int baseAttack;
    private int baseDefense;
    private ArrayList<BattleGear> battleGears;

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
        battleGears = new ArrayList<>();
    }

    public void setOwner(){
        this.owner = owner;
    }

    //initialize with weapons and guards
    public StandardBattleStat(int maxHealth, int attack, int defense, ArrayList<BattleGear> battleGears){
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.baseAttack = attack;
        this.baseDefense = defense;
        this.battleGears = battleGears;
    }

    @Override
    public int getAttack() {
        int totalAttack;
        for (BattleGear battleGear : battleGears){
            battleGear.modifyStates(this);
        }

        totalAttack = (baseAttack + flatAttackIncrease) * multiplyAttackIncrease; 
        resetModifiers();
        return totalAttack;
    }

    @Override
    public int getReducedAttack(int damage) {
        int reducedDamage;
        for (BattleGear battleGear : battleGears){
            battleGear.modifyStates(this);
        } 
        reducedDamage = (damage - baseDefense - flatDefenseIncrease) / multiplayDefenseIncrease;
        resetModifiers();
        return reducedDamage;        
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
    public void addBattleGear(BattleGear battleGear){
        battleGears.add(battleGear);
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
        // if (currentHealth <= 0 && owner instanceof Player){
        //     for (Collectable collectable : ((Player)owner).getNonBattleItems()){
        //         if (collectable instanceof Ring){
        //             currentHealth = maxHealth;
        //             collectable.removeFromInventory();
        //         }
        //     }
        // }
    }

    @Override
    public void recoverToFull(){
        currentHealth = maxHealth;
    }

    @Override
    public ArrayList<BattleGear> getBattleGears() {
        return battleGears;
    }


}

package dungeonmania.entity.creature;

import java.util.ArrayList;

import dungeonmania.entity.Entity;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.square.Boulder;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;
import java.util.List;

public abstract class Creature extends Entity{
    private BattleStat battleStat;
    private List<Collectable> nonBattleItems; 
    
    public Creature(DungeonMapAPI game, String type, Position position, BattleStat battleStat) {
        super(game, position, type);
        this.battleStat = battleStat;
        this.nonBattleItems = new ArrayList<>();
    } 

    //TODO NOT MENTIONED IN UML
    public void addCollectable(Collectable newItem){
        // if (newItem instanceof Weapon){
        //     battleStat.addWeapon((Weapon)newItem);
        // }
        // else if(newItem instanceof Guard){
        //     battleStat.addGuard((Guard)newItem);
        // }
        if (newItem instanceof BattleGear)
            battleStat.addBattleGear((BattleGear)newItem);
        else{
            nonBattleItems.add(newItem);
        }
    }

    public void removeCollectable(Collectable item){
        nonBattleItems.remove(item);
    }

    @Override
    public boolean canBeOnSamePosition(Boulder boulder){
        return false;
    }

    //getter setters
    public BattleStat getBattleStat() {
        return battleStat;
    }

    public void setBattleStat(BattleStat battleStat) {
        this.battleStat = battleStat;
    }

    public List<Collectable> getNonBattleItems() {
        return nonBattleItems;
    }

    public void setNonBattleItems(ArrayList<Collectable> nonBattleItems) {
        this.nonBattleItems = nonBattleItems;
    }

    public ArrayList<BattleGear> getBattleGears(){
        return battleStat.getBattleGears();
    }

    public Collectable getNonBattleItemFromInventory(String id){
        for (Collectable collectable : nonBattleItems){
            if (collectable.getId().equals(id)){
                return collectable;
            }
        }
        return null;
    }
}

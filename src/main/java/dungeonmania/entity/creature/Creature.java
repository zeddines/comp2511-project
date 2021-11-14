package dungeonmania.entity.creature;

import java.util.ArrayList;
import java.util.HashMap;

import dungeonmania.entity.Entity;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Effect;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.square.Boulder;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;
import java.util.List;
import java.util.Map;

public abstract class Creature extends Entity{
    private BattleStat battleStat;
    private ArrayList<Collectable> nonBattleItems; 

    Map<String, Integer> buffs;
    
    
    public Creature(DungeonMapAPI game, String type, Position position) {
        super(game, position, type);
        this.nonBattleItems = new ArrayList<>();
        buffs = new HashMap<>();
        buffs.put("invisibility", 0);
        buffs.put("invincibility", 0);
    }

    public void setPosition(Position position) {
         super.setPosition(position);
    }

    public Position getPosition() {
        return super.getPosition();
    }

    
    
    public void applyInvisibleBuff() {
        buffs.put("invisibility", buffs.get("invisibility").intValue() + 1);
    }


    public void applyInvincibleBuff() {
        buffs.put("invincibility", buffs.get("invincibility").intValue() + 1);
    }

    public void revertInvisibleBuff() {
        buffs.put("invisibility", buffs.get("invisibility").intValue() - 1);
    }


    public void revertInvincibleBuff() {
        buffs.put("invincibility", buffs.get("invincibility").intValue() - 1);
    }



    //getter setters
    public boolean isInvisible() {
        return buffs.get("invisibility") > 0;
    }

    public boolean isInvincible() {
        return buffs.get("invincibility") > 0;
    }

    public void addCollectable(Collectable newItem){
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

    public BattleStat getBattleStat() {
        return battleStat;
    }

    public void setBattleStat(BattleStat battleStat) {
        this.battleStat = battleStat;
    }

    public ArrayList<Collectable> getNonBattleItems() {
        return nonBattleItems;
    }

    public void setNonBattleItems(ArrayList<Collectable> nonBattleItems) {
        this.nonBattleItems = nonBattleItems;
    }

    public ArrayList<BattleGear> getBattleGears(){
        return battleStat.getBattleGears();
    }

    public ArrayList<Collectable> getAllCollectables(){
        ArrayList<Collectable> retList = new ArrayList<>();
        retList.addAll(nonBattleItems);
        for (BattleGear item : getBattleGears()){
            retList.add((Collectable)item);
        }
        return retList;
    }

    public void removeItemOfType(String type){
        for (Collectable item : getAllCollectables()){
            if (item.getType().equals(type)){
                removeCollectable(item);
                return;
            }
        }
    }
}

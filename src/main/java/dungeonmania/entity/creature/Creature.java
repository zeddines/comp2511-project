package dungeonmania.entity.creature;

import java.util.ArrayList;

import dungeonmania.entity.Entity;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.Guard;
import dungeonmania.entity.interfaces.Weapon;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;
import java.util.List;

public abstract class Creature extends Entity{
    //TODO NOT MENTIONED IN UML(CREATURE SHOULDN'T HAVE INVENTORY AND CHANGE BATTLESTAT NAME) 
    private BattleStat battleStat;

    private List<Collectable> nonBattleItems; 
    
    public Creature(DungeonMapAPI game, String type, Position position, boolean isInteractable , BattleStat battleStat) {
        super(game, position, type, isInteractable);
        this.battleStat = battleStat;
        this.nonBattleItems = new ArrayList<>();
    }

    //TODO NOT MENTIONED IN UML
    public void addCollectable(Collectable newItem){
        if (newItem instanceof Weapon){
            battleStat.addWeapon((Weapon)newItem);
        }
        else if(newItem instanceof Guard){
            battleStat.addGuard((Guard)newItem);
        }
        else{
            nonBattleItems.add(newItem);
        }
    }

    public void removeCollectable(Collectable item){
        if (item instanceof Weapon){
            battleStat.removeWeapon((Weapon)item);
        }
        else if(item instanceof Guard){
            battleStat.removeGuard((Guard)item);
        }
        else{
            nonBattleItems.remove(item);
        }
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

    public ArrayList<Weapon> getOwnedWeapons(){
        return battleStat.getWeapons();
    }

    public ArrayList<Guard> getOwnedGuards(){
        return battleStat.getGuards();
    }

    public Collectable getNonBattleItemFromInventory(String id){
        for (Collectable collectable : nonBattleItems){
            if (collectable.getId() == id){
                return collectable;
            }
        }
        return null;
    }
}

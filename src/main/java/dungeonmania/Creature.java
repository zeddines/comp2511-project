package dungeonmania;

import java.util.ArrayList;

import dungeonmania.util.Position;

public class Creature extends Entity{
    //TODO NOT MENTIONED IN UML(CREATURE SHOULDN'T HAVE INVENTORY AND CHANGE BATTLESTAT NAME) 
    private BattleStat battleStat;

    private ArrayList<Collectable> nonBattleItems; 
    
    public Creature(DungeonManiaController game, String id, String type, Position position, boolean isInteractable ,BattleStat battleStat) {
        super(game, id, type, position, isInteractable);
        this.battleStat = battleStat;
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

    //TODO NOT MENTIONED IN UML 
    public ArrayList<Collectable> getAllOwned(){
        
    }

    //getter setters
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

    public ArrayList<Weapon> getOwnedWeapons(){
        return battleStat.getWeapons();
    }

    public ArrayList<Guard> getOwnedGuards(){
        return battleStat.getGuards();
    }
}

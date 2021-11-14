package dungeonmania.entity.creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Effect;
import dungeonmania.entity.collectable.Key;
import dungeonmania.entity.collectable.Potion;
import dungeonmania.entity.collectable.SunStone;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.Usable;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Player extends Creature{

    public Player(Position position, String type, DungeonMapAPI map) {
        super(map, type, position);
        map.setPlayer(this);
    }

    
    public void use(String id) throws IllegalArgumentException, InvalidActionException{
        for (Collectable collectable : getAllCollectables()){
            if (id.equals(collectable.getId())){
                if (!(collectable instanceof Usable)){
                    throw new IllegalArgumentException("item is not usable");
                }
                else{
                    Usable item = (Usable)collectable;    
                    item.use();
                    return;
                }
            }
        }
        throw new InvalidActionException("item is not in player's inventory");
    }

    public ArrayList<ItemResponse> inventoryToItemResponseList(){
        ArrayList<ItemResponse> returnList = new ArrayList<>();

        for (Collectable item : getNonBattleItems()){
            returnList.add(new ItemResponse(item.getId(), item.getType()));
        }

        for (BattleGear battleGear : getBattleGears()){
            Entity battleGearAsEntity = (Entity)battleGear;
            returnList.add(new ItemResponse(battleGearAsEntity.getId(), battleGearAsEntity.getType()));
        }

        return returnList;
    }

    public ArrayList<Collectable> give(List<String> itemsRequired){
        ArrayList<Integer> positionOfItems = new ArrayList<>();
        for (String itemRequired : itemsRequired){
            boolean itemIsMissing = true;
            for (int i = 0; i < getNonBattleItems().size() && itemIsMissing; i++){
                Collectable itemAvailable = getNonBattleItems().get(i);
                if (itemRequired.equals(itemAvailable.getType()) && !positionOfItems.contains(i)){
                    itemIsMissing = false;
                    positionOfItems.add(i);
                }
            }
            if (itemIsMissing){
                return null;
            }
        }
        ArrayList<Collectable> collectablesToGive = new ArrayList<>();
        for (int i : positionOfItems){
            if (!(getNonBattleItems().get(i) instanceof SunStone)){
                collectablesToGive.add(getNonBattleItems().get(i));
            }
        }
        for (Collectable collectableToRemove : collectablesToGive){
            getNonBattleItems().remove(collectableToRemove);
        }
        return collectablesToGive;
    }
}

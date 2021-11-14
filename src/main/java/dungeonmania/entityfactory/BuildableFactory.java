package dungeonmania.entityfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import dungeonmania.entity.buildable.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class BuildableFactory extends PrimaryFactory {

    public static final String[] buildables = {"bow", "shield", "sceptre", "midnight_armour"};    

    public BuildableFactory(FactoryFront factory) {
        super(buildables, factory);
    }

    @Override
    public Entity build(JSONObject entityContents) {
        return null;
    }

    public Collectable makeBuildable(String type){
        ArrayList<Collectable> items = getMap().getPlayer().getAllCollectables();
        List<String> components = canBuildWithComponent(type, items);
        if (components == null)
            return null;
        else
            removeItemsFromPlayer(components);
        switch(type){
            case "bow":
                return new Bow(type, getMap(), getMap().getPlayer(), 5);
            case "shield":
                return new Shield(type, getMap(), getMap().getPlayer(), 5);
            case "sceptre":
                return new Sceptre(getMap(), type, getMap().getPlayer());
            case "midnight_armour":
                return new MidnightArmour(getMap(), type, getMap().getPlayer(), 5);
            default:
                return null;
        }
    }  

    public List<String> canBuildWithComponent(String type, ArrayList<Collectable> items){
        switch(type){
            case "bow":
                if (countBuildableItems("wood", items) >= 1 && countBuildableItems("arrow", items) >= 3)
                    return Arrays.asList("wood", "arrow", "arrow", "arrow");
                return null;
            case "shield":
                if (countBuildableItems("wood", items) >= 2){
                    if (countBuildableItems("key", items) >= 1){
                        return Arrays.asList("wood", "wood", "key");
                    }
                    else if (countBuildableItems("treasure", items) >= 1){
                        return Arrays.asList("wood", "wood", "treasure");
                    }
                }
            case "sceptre":
                if (countBuildableItems("sun_stone", items) == 0){
                    return null;
                }
                if (countBuildableItems("wood", items) >= 1){
                    if (countBuildableItems("key", items) >= 1){
                        return Arrays.asList("sun_stone", "wood", "key");
                    }
                    else if (countBuildableItems("treasure", items) >= 1){
                        return Arrays.asList("sun_stone", "wood", "treasure");
                    }
                }
                else if (countBuildableItems("arrow", items) >= 2){
                    if (countBuildableItems("key", items) >= 1){
                        return Arrays.asList("sun_stone", "arrow", "arrow", "key");
                    }
                    else if (countBuildableItems("treasure", items) >= 1){
                        return Arrays.asList("sun_stone", "arrow", "arrow", "treasure");
                    }
                }
                return null;
            case "midnight_armour":
                DungeonMapAPI map = getMap();
                for (EntityAPI entity : map.getAllEntitiesInMap()){
                    if ("zombie_toast".equals(entity.getType())){
                        return null;
                    }
                }
                if (countBuildableItems("armour", items) >= 1 && countBuildableItems("sun_stone", items) >= 1)
                    return Arrays.asList("armour", "sun_stone");
                return null;
            default:
                return null;
        }      
    }
    
    public int countBuildableItems(String type, ArrayList<Collectable> items){  
        int count = 0;
        for (Collectable item: items)
            if (item.getType().equals(type))
                count++;

        return count;
    }

    public void removeItemsFromPlayer(List<String> types){
        Player player = getMap().getPlayer();
        for (String itemType : types){
            player.removeItemOfType(itemType);
        }
    }
}

package dungeonmania.entityfactory;
import java.util.ArrayList;

import org.json.JSONObject;
import dungeonmania.entity.*;
import dungeonmania.entity.buildable.Bow;
import dungeonmania.entity.collectable.*;
import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class CollectibleFactory extends PrimaryFactory {

    public static final String[] collectibles = {"one_ring", "treasure", "key", "health_potion", "invincibility_potion", "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour", "anduril", "sun_stone"};


    public CollectibleFactory(FactoryFront factory) {
        super(collectibles, factory);      
    }

    @Override
    public Entity build(JSONObject entityContents) {
        Position position = new Position(entityContents.getInt("x"), entityContents.getInt("y"));
        String type = entityContents.getString("type");
        DungeonMap map = getMap();

        switch(type){
            case "treasure": 
                return new Treasure(position, type, map);
            case "key": 
                return new Key(position, type, entityContents.getInt("key"), map);
            case "health_potion": 
                return new Potion(position, type, map, makeEffect("RecoverHealthEffect", null));   
            case "invincibility_potion":
                return new Potion(position, type, map, makeEffect("InvincibilityEffect", null));
            case "invisibility_potion":
                return new Potion(position, type, map, makeEffect("InvisibilityEffect", null));
            case "wood":
                return new Wood(position, type, map);
            case"arrow":
                return new Arrow(position, type, map);
            case"bomb":
                return new Bomb(position, type, map, 2);
            case "armour":
                return new Armour(position, type, map, 5);   
            case "sword":
                return new Sword(position, type, map, 5); 
            case"anduril":
                return new Anduril(position, type, map, 5);
            case "sun_stone":
                return new SunStone(position, type, map);
            default:
                return null;
        }
    }

    public Collectable makeCollectable(String type, Creature owner){
        DungeonMapAPI map = getMap();
        switch(type){           
            case "sword":
                return new Sword(type, map, owner, 5);
            case "armour":
                return new Armour(type, map, owner, 5);  
            case "anduril":
                return new Anduril(owner, map, type, 5);            
            case "health_potion":
                return new Potion(owner, type, map, makeEffect("RecoverHealthEffect", owner));   
            case "invisibility_potion":
                return new Potion(owner, type, map, makeEffect("InvisibilityEffect", owner));               
            case "invincibility_potion":
                return new Potion(owner, type, map, makeEffect("InvincibilityEffect", owner));  
            case "one_ring":
                return new Ring(type, map, owner);
            default:
                return null;                
        }
    }

    private Effect makeEffect(String effect, Creature target){
        String difficulty = getDifficulty();
        DungeonMapAPI game = getMap();
        switch(effect){
            case "RecoverHealthEffect":
                return difficulty.equals("Hard") ? new NoEffect(target, game) : new RecoverHealthEffect(target, game);             
            case "InvisibilityEffect":
                return difficulty.equals("Hard") ? new InvisibilityEffect(target, 3, game) : new InvisibilityEffect(target, 15, game);                
            case "InvincibilityEffect":
            return difficulty.equals("Hard") ? new NoEffect(target, game) : new InvincibilityEffect(target, 5, game);                
            default: 
                return null;
        }
    }
}

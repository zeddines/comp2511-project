package dungeonmania.entityfactory;
import org.json.JSONObject;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class CollectibleFactory extends PrimaryFactory {

    public static final String[] collectibles = {"treasure", "key", "health_potion", "invincibility_potion", "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour", "anduril", "sceptre", "midnight_armour"};

    public CollectibleFactory(String difficulty) {
        super(collectibles, difficulty);
    }
    
    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        if (type.equals("treasure"))
            return new Treasure(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("key"))
            return new Key(new Position(entityContents.getInt("x"), entityContents.getInt("y")), entityContents.getString("type"),  entityContents.getInt("key"), map);
        else if(type.equals("health_potion"))
                return new Potion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"),map, new RecoverHealthEffect(null, map));   
        else if(type.equals("invincibility_potion"))
            return new Potion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map, new InvincibilityEffect(null, 30, map));
        else if (type.equals("invisibility_potion"))
            return new Potion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map, new InvisibilityEffect(null, 30, map));
        else if (type.equals("wood"))
            return new Wood(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("arrow"))
            return new Arrow(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("bomb"))
            return new Bomb(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("armour"))
            return new Armour(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);   
        else if (type.equals("sword"))
            return new Sword(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map); 
        else if (type.equals("anduril")){
            return new Anduril(new Position(entityContents.getInt("x"), entityContents.getInt("y")), entityContents.getString("type"), map);
        }
        else
            return   null;
    }
    

    //public makePotion(String type, DungeonMapAPI map){
    //    switch(type){
    //        case "health_potion":
    //            if (difficulty.equals("hard"))
    //                return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"),map, new NoEffect());                       
    //            else
    //                return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"),map, new RecoverHealthEffect()); 
    //        case "invisibility_potion":
    //            return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map, new InvisibilityEffect(30));
    //        case "invincibility_potion":
    //            return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map, new InvincibilityEffect(30));
    //    }
    //}

    public Collectable makeCollectables(String type, Position current, DungeonMapAPI map){
        switch(type){
            case "treasure":
                Treasure treasure = new Treasure(current, type, map);
                return treasure;                
            case "key":
                Key key = new Key(current, type, map);
                return key;                
            case "wood":
                Wood wood = new Wood(current, type, map);
                return wood;                
            case "arrow":
                Arrow arrow = new Arrow(current, type, map);
                return arrow;                
            case "sword":
                Sword sword = new Sword(current, type, map); 
                return sword;                
            case "armour":
                Armour armour = new Armour(current, type, map); 
                return armour;                
            case "health_potion":
                Potion healthPotion = new Potion(current, type, map, makeEffect("RecoverHealthEffect"));
                return healthPotion;                
            case "invisibility_potion":
                Potion invisiblePotion = new Potion(current, type, map, makeEffect("InvisibilityEffect"));
                return invisiblePotion;                
            case "invincibility_potion":
                Potion invinciblePotion = new Potion(current, type, map, makeEffect("InvincibilityEffect"));
                return invinciblePotion;                
            default:
                return null;                
        }
    }

    public PotionEffect makeEffect(String effect){
        switch(effect){
            case "RecoverHealthEffect":
                return getDifficulty().equals("hard") ? new NoEffect() : new RecoverHealthEffect();                
            case "InvisibilityEffect":
                return getDifficulty().equals("hard") ? new InvisibilityEffect(3) : new InvisibilityEffect(15);                
            case "InvincibilityEffect":
                return new InvincibilityEffect(30);
            default: 
                return null;
        }
    }
    
}

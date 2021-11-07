package dungeonmania.entityfactory;
import org.json.JSONObject;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class CollectibleFactory extends PrimaryFactory {

    public static final String[] collectibles = {"treasure", "key", "health_potion", "invincibility_potion", "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour"};

    public CollectibleFactory() {
        super(collectibles);
    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        if (type.equals("treasure"))
            return new Treasure(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("key"))
            return new Key(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if(type.equals("health_potion"))
            return new Potion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"),map, new RecoverHealthEffect());            
        else if(type.equals("invincibility_potion"))
            return new Potion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map, new InvincibilityEffect(30));
        else if (type.equals("invisibility_potion"))
            return new Potion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map, new InvisibilityEffect(30));
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
        else
            return null;
    }

    public makePotion(String type, DungeonMapAPI map){
        switch(type){
            case "health_potion":
                if (difficulty.equals("hard"))
                    return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"),map, new NoEffect());                       
                else
                    return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"),map, new RecoverHealthEffect()); 
            case "invisibility_potion":
                return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map, new InvisibilityEffect(30));
            case "invincibility_potion":
                return new Potion(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map, new InvincibilityEffect(30));
        }
    }

    public makeCollectable(String type, Position current, DungeonMapAPI map){
        switch(type){
            case "treasure":
                return new Treasure(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
            case "key":
                return new Key(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
            case "wood":
                return new Wood(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
            case "arrow":
                return new Arrow(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map);
            case "sword":
                return new Sword(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map); 
            case "armour":
                return new Armour(new Position(type.getInt("x"), type.getInt("y")),type.getString("type"), map); 
        }
    }
    
}

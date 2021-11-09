package dungeonmania.entityfactory;
import org.json.JSONObject;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class CollectibleFactory extends PrimaryFactory {

    public static final String[] collectibles = {"treasure", "key", "health_potion", "invincibility_potion", "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour", "anduril", "sceptre", "midnight_armour"};

    public CollectibleFactory() {
        super(collectibles);
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
    
}

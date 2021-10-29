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
        if (type.equals("treasuse"))
            return new Treasure(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("key"))
            return new Key(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if(type.equals("health_potion"))
            return new HealthPotion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"),map);
        else if(type.equals("invincibility_potion"))
            return new InvincibilityPotion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("invisibility_potion"))
            return new InvisibilityPotion(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("wood"))
            return new Wood(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("arrow"))
            return new Arrow(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else if (type.equals("bomb"))
            return new Bomb(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
        else
            return new Armour(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);       
    }
    
}

package dungeonmania.entityfactory;
import org.json.JSONObject;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.*;

public class CollectibleFactory extends PrimaryFactory {

    public static final String[] collectibles = {"treasure", "key", "health_potion", "invincibility_potion", "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour"};

    public CollectibleFactory() {
        super(collectibles);
    }

    @Override
    public Entity build(JSONObject entityContents) {
        String type = entityContents.getString("type");
        if (type.equals("treasuse"))
            return new Treasure(entityContents);
        else if (type.equals("key"))
            return new Key(entityContents);
        else if(type.equals("health_potion"))
            return new HealthPotion(entityContents);
        else if(type.equals("invincibility_potion"))
            return new InvincibilityPotion(entityContents);
        else if (type.equals("invisibility_potion"))
            return new InvisibilityPotion(entityContents);
        else if (type.equals("wood"))
            return new Wood(entityContents);
        else if (type.equals("arrow"))
            return new Arrow(entityContents);
        else if (type.equals("bomb"))
            return new Bomb(entityContents);
        else
            return new Armour(entityContents);
     

            
    }
    
}

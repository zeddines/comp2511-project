package dungeonmania.entityfactory;

public class CollectibleFactory extends PrimaryFactory {

    public static final String[] collectibles = {"treasure", "key", "health_potion", "invincibility_potion", "invisibility_potion", "wood", "arrow", "bomb", "sword", "armour"};

    public CollectibleFactory() {
        super(collectibles);
    }
    
}

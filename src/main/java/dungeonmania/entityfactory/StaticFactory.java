package dungeonmania.entityfactory;

public class StaticFactory extends PrimaryFactory {

    public static String[] staticEntities = {"wall", "exit", "boulder", "switch", "door", "portal", "zombie_toast_spawner"}; 
    
    public StaticFactory() {
        super(staticEntities);
    }
}

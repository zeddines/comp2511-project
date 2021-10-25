package dungeonmania.entityfactory;


public class BuildableFactory extends PrimaryFactory {

    public static final String[] buildables = {"bow", "shield"};

    public BuildableFactory() {
        super(buildables);
    }
    
}

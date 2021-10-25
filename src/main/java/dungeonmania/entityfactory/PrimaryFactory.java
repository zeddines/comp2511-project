package dungeonmania.entityfactory;

import java.util.Arrays;
import java.util.List;
import dungeonmania.entities.*;

abstract public class PrimaryFactory implements FactoryExtendAPI {

    private List<String> entities;

    public PrimaryFactory(String[] entities) {
        this.entities = Arrays.asList(entities);
    }

    public boolean check(String type) {
        return entities.contains(type);
    }

    abstract public Entity build(String type);
    }
    
}

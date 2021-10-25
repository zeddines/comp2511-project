package dungeonmania.entityfactory;
import dungeonmania.entities.*;

public class FactoryFront implements FactoryAPI {

    private BuildableFactory bFac = new BuildableFactory();
    private CollectibleFactory cFac = new CollectibleFactory();
    private MovingFactory mFac = new MovingFactory();
    private StaticFactory sFac = new StaticFactory();
    private RareFactory rFac = new RareFactory();

    public FactoryFront() {

    }

    public Entity build(String type) {

    }


    
}

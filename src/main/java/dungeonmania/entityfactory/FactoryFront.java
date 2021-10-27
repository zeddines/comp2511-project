package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import org.json.JSONObject;

public class FactoryFront implements FactoryAPI {

    private BuildableFactory bFac = new BuildableFactory();
    private CollectibleFactory cFac = new CollectibleFactory();
    private MovingFactory mFac = new MovingFactory();
    private StaticFactory sFac = new StaticFactory();
    private RareFactory rFac = new RareFactory();

    public FactoryFront() {

    }

    public Entity build(JSONObject entityContents) {
        String type = entityContents.getString("type");
        if (bFac.checkType(type))
            return bFac.build(entityContents);
        else if (cFac.checkType(type))
            return cFac.build(entityContents);
        else if (mFac.checkType(type))
            return mFac.build(entityContents);
        else if (sFac.checkType(type))
            return sFac.build(entityContents);
        else
            return rFac.build(entityContents);
    }
   
}

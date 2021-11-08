package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.map.DungeonMapAPI;

import org.json.JSONObject;

public class FactoryFront implements FactoryAPI {

    private BuildableFactory bFac = new BuildableFactory();
    private CollectibleFactory cFac = new CollectibleFactory();
    private MovingFactory mFac = new MovingFactory();
    private StaticFactory sFac = new StaticFactory();
    //private RareFactory rFac = new RareFactory();

    public FactoryFront() {

    }

    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        /*if (bFac.checkType(type))
            return bFac.build(entityContents, map);
        else */
        
        if (cFac.checkType(type))
            return cFac.build(entityContents, map);
        else if (mFac.checkType(type))
            return mFac.build(entityContents, map);
        //else if (sFac.checkType(type))
        else
            return sFac.build(entityContents, map);
        // else
        //     return rFac.build(entityContents, map);
    }
   
}

package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;
import dungeonmania.entityfactory.*;
import dungeonmania.entityfactory.CollectibleFactory;

import org.json.JSONObject;

public class FactoryFront implements FactoryAPI {

    private BuildableFactory bFac = new BuildableFactory();
    private CollectibleFactory cFac = new CollectibleFactory();
    private MovingFactory mFac = new MovingFactory();
    private StaticFactory sFac = new StaticFactory();
    private RareFactory rFac = new RareFactory();

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
        else if (sFac.checkType(type))
            return sFac.build(entityContents, map);
        else
            return rFac.build(entityContents, map);
    }

    public Collectable makeCollectable(String type, Position current, DungeonMapAPI map){
        if(type.equals("health_potion") || type.equals("invisibility_potion") || type.equals("invincibility_potion")){
            cFac.makePotion(type, map);
        }
        if (type.equals("treasure") || type.equals("key") || type.equals("wood") || type.equals("arrow")){
            cFac.makeCollectable(type, map);
        }
    }

    public Creature makeCreature(String type, Position current, DungeonMapAPI map){
        if(type.equals("zombie_toast") || type.equals("spider") || type.equals("mercenary")){
            mFac.makeEnemy(type, map);
        }
    }

    public Static makeStatic(String type, Position current, DungeonMapAPI map){
        if(type.equals("wall") || type.equals("exit") || type.equals("boulder") || type.equals("switch") || type.equals("door") || type.equals("zombie_toast_spawner")){
            sFac.makeStatic(type, current, map);
        }
    }

    public Rare makeRare(String type, Position current, DungeonMapAPI map){
        rFac.makeOneRing(type, current, map);
    }







    

     


   
}

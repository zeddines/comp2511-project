package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;
import dungeonmania.entityfactory.*;
import dungeonmania.entityfactory.CollectibleFactory;

import java.util.ArrayList;

import org.json.JSONObject;

public class FactoryFront implements FactoryAPI {

    private BuildableFactory bFac = new BuildableFactory();
    private CollectibleFactory cFac = new CollectibleFactory();
    private MovingFactory mFac = new MovingFactory();
    private StaticFactory sFac = new StaticFactory();
    //private RareFactory rFac = new RareFactory();

    private BuildableFactory bFac;
    private CollectibleFactory cFac;
    private MovingFactory mFac;
    private StaticFactory sFac;
    private RareFactory rFac;

    public FactoryFront(String difficulty, DungeonMapAPI map) {
        bFac = new BuildableFactory(difficulty, map);
        cFac = new CollectibleFactory(difficulty, map);
        mFac = new MovingFactory(difficulty, map);
        sFac = new StaticFactory(difficulty, map);
        rFac = new RareFactory(difficulty, map);
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

    public Collectable makeCollectable(String type, Position current){
        // if(type.equals("health_potion") || type.equals("invisibility_potion") || type.equals("invincibility_potion")){
        //     cFac.makePotion(type, map);
        // }
        // if (type.equals("treasure") || type.equals("key") || type.equals("wood") || type.equals("arrow")){
        //     cFac.makeCollectables(type, map);
        // }
        cFac.makeCollectables(type, current);
    }

    public Collectable makeBuildable(String type, ArrayList<Collectable> nonBattleItems){
        if  (type.equals("Bow") || type.equals("Shield")){
            
        }
    }

    public Creature makeCreature(String type, Position current, DungeonMapAPI map, ArrayList<String>collectables){
        // if(type.equals("zombie_toast") || type.equals("spider") || type.equals("mercenary")){
        //     mFac.makeEnemy(type, map);
        // }
        Creature newCreature =  mFac.makeEnemy(type, current, map);
        for ( ){
            newCreature.addCollectable(cFac.makeCollectables(, current, map););
        }
        return newCreautre;
    }

    public Static makeStatic(String type, Position current, DungeonMapAPI map){
        //if(type.equals("wall") || type.equals("exit") || type.equals("boulder") || type.equals("switch") || type.equals("door") || type.equals("zombie_toast_spawner")){
            sFac.makeStatics(type, current, map);
        //}
    }

    // public Rare makeRare(String type, Position current, DungeonMapAPI map){
    //     rFac.makeOneRing(type, current, map);
    // }
     
   
}

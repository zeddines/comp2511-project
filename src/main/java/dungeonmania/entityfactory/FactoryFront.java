package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;
import dungeonmania.entityfactory.*;
import dungeonmania.entityfactory.CollectibleFactory;

import java.util.ArrayList;

import org.json.JSONObject;

public class FactoryFront implements FactoryAPI {
    private String difficulty;
    private DungeonMap map;

    private BuildableFactory bFac;
    private CollectibleFactory coFac;
    private CreatureFactory crFac;
    private StaticFactory sFac;

    public FactoryFront(String difficulty, DungeonMap map) {
        this.difficulty = difficulty;
        this.map = map;
        bFac = new BuildableFactory(this);
        coFac = new CollectibleFactory(this);
        crFac = new CreatureFactory(this);
        sFac = new StaticFactory(this);
    }

    public Entity build(JSONObject entityContents) {
        String type = entityContents.getString("type");
        if (bFac.checkType(type))
            return bFac.build(entityContents);
        else if (coFac.checkType(type))
            return coFac.build(entityContents);
        else if (crFac.checkType(type))
            return crFac.build(entityContents);
        else if (sFac.checkType(type))
            return sFac.build(entityContents);
        else
            return null;
    }

    public ArrayList<Collectable> makeCollectables(ArrayList<String> itemTypes, Creature owner){
        ArrayList<Collectable> retList = new ArrayList<>();
        for (String type : itemTypes){
            retList.add(coFac.makeCollectable(type, owner));
        }
        return retList;
    }

    public Collectable makeCollectable(String type, Creature owner){
        return coFac.makeCollectable(type, owner);
    }

    public Collectable makeBuildable(String type){
        return bFac.makeBuildable(type);
    }

    public Enemy makeEnemy(String type, Position position, ArrayList<String> collectables){
        Enemy newEnemy = makeEnemy(type, position);
        for (String collectableType : collectables){
            newEnemy.addCollectable(coFac.makeCollectable(collectableType, newEnemy));
        }
        return newEnemy;
    }

    public Enemy makeEnemy(String type, Position position){
        return crFac.makeEnemy(type, position);
    }


    //getters
    public ArrayList<String> getAllBuildableItems(ArrayList<Collectable> collectables){
        ArrayList<String> retList = new ArrayList<>();
        for (String type : BuildableFactory.buildables){
            if (bFac.canBuildWithComponent(type, collectables) != null)
                retList.add(type);
        }
        return retList;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public DungeonMap getMap(){
        return map;
    }

}

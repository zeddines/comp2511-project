package dungeonmania.map;
import dungeonmania.util.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import dungeonmania.entity.EntityAPI;
import dungeonmania.response.models.*;

import java.util.List;

public class DungeonMap {

    private Map<Position,List<EntityAPI>> entities;
    private List<EntityAPI> inventory;
    private String goals;

    public DungeonMap() {
        entities = new Hashtable<>();
        inventory = new ArrayList<>();
    }

    public void addEntity(EntityAPI newEntity) {
        if (!entities.containsKey(newEntity.getPosition())) {
            entities.put(newEntity.getPosition(), new ArrayList<EntityAPI>());
        }
            List<EntityAPI> addNew = entities.get(newEntity.getPosition());
            addNew.add(newEntity);
    }

    public List<EntityResponse> getInfoList() {
        List<EntityResponse> info = new ArrayList<>();
        entities.entrySet().stream().map(e -> e.getValue()).forEach(e->e.stream().forEach(k -> info.add(k.getInfo())));
        return info;
    }

    public String getGoals() {
        return goals;
    }
    public void setGoals(String goals) {
        this.goals = goals;
    }
       


    
}

package dungeonmania.map;
import dungeonmania.util.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import dungeonmania.entities.*;
import java.util.List;

public class DungeonMap {

    private Map<Position,EntityAPI> entities;
    private List<EntityAPI> inventory;

    public DungeonMap() {
        entities = new Hashtable<>();
        inventory = new ArrayList<>();
    }

    
}

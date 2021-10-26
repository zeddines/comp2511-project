package dungeonmania.entity.creature;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

public class Creature {
    // private MovementBehaviour movement; 
    // private BattleStat battle; 
    List<Object> inventory; 

    public Creature() {
        this.inventory = new ArrayList<Object>(); 
    }
}

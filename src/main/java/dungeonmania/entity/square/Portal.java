package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

import java.util.ArrayList;

import org.json.JSONObject;

public class Portal extends Entity {

    String colour;

    /**
     *  Teleports entities to a corresponding portal 
     * colour refers to the corresponding portal -> will need to fix this
     */
    public Portal(Position current, String type, String colour, DungeonMapAPI map) {
       super(map, current, type);
        this.colour = colour;
    }

    public Position teleportLocation() {
        ArrayList<EntityAPI> entitiesArray = getGame().getAllEntitiesInMap();
        for (EntityAPI entity : entitiesArray){
            if (entity instanceof Portal && entity != this){
                return entity.getPosition();
            }
        }
        return null;
    }

}

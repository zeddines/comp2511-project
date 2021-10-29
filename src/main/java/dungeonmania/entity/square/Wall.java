package dungeonmania.entity.square;

import dungeonmania.entity.Entity;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Wall extends Entity {
    public Wall(Position current, String type, DungeonMapAPI map) {
        super(current, type, false, false, map); 
    }

    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }

    /*/**
     *  Blocks the movement of the character, enemies and boulders 
     */
   /* @Override
    public void movement(String s) {
        return; 
    }*/
}

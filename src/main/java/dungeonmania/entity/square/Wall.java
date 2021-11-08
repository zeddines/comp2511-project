package dungeonmania.entity.square;

import dungeonmania.entity.Entity;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.creature.Spider;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Wall extends Entity {
    public Wall(Position current, String type, DungeonMapAPI map) {
        super(map, current, type); 
    }


    /*/**
     *  Blocks the movement of the character, enemies and boulders 
     */
   /* @Override
    public void movement(String s) {
        return; 
    }*/

    //nothing occurs 
    @Override
    public boolean canBeOnSamePosition(Player player) {
        return false;
    }

    @Override 
    public boolean canBeOnSamePosition(Enemy enemy){
        if (enemy instanceof Spider)
            return true;
        return false;
    }

    @Override
    public boolean canBeOnSamePosition(Boulder boulder){
        return false;
    }
}

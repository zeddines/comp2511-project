package dungeonmania;
import dungeonmania.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
import dungeonmania.game.Game;
import dungeonmania.map.DungeonMap;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;


public class BoulderTest {
        
    @Test 
    public void testBoulder(){  
        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse newResponse = newController.newGame("boulders", "Peaceful");
        List<EntityResponse>  newEntities = newResponse.getEntities();

        //Get the positions of every single boulder
        Position newPosition = new Position(3,2);
        Position newPosition2 = new Position(4,3);
        Position newPosition3 = new Position(4,4);
        Position newPosition4 = new Position(1,6);
        Position newPosition5 = new Position(3,6);
        Position newPosition6 = new Position(4,6);
        Position newPosition7 = new Position(5,6);

        //Make a list to store all the boulder positions
        List<Position> boulderPositionList = new ArrayList<>();  
        boulderPositionList.add(newPosition);
        boulderPositionList.add(newPosition2);
        boulderPositionList.add(newPosition3);
        boulderPositionList.add(newPosition4);
        boulderPositionList.add(newPosition5);
        boulderPositionList.add(newPosition6);
        boulderPositionList.add(newPosition7);

        boolean positionCorrect = false;
        int numBoulders = 0;
        for (EntityResponse entity: newEntities) {            
            if (entity.getType().equals("boulder")) {
                if (boulderPositionList.contains(entity.getPosition())){
                    boulderPositionList.remove(entity.getPosition());                                                                
                }
                else {
                    break;
                }
            }              
        }

        if (boulderPositionList.isEmpty()){
            positionCorrect = true;
        }

        assertTrue(positionCorrect);
    }     
    
    @Test
    public void testBoulderMovement(){
        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse newResponse = newController.newGame("boulders", "Peaceful");
        List<EntityResponse> newEntities = newResponse.getEntities();

        EntityResponse player = null;
        ArrayList<EntityResponse> boulders = new ArrayList<>();
        for (EntityResponse entity : newEntities){
            if (entity.getType().equals("player"))
                player = entity;
            else if (entity.getType().equals("boulder")){
                boulders.add(entity);
            }
        }
        String boulderId = null;
        for (EntityResponse boulder : boulders){
            if (boulder.getPosition().equals(new Position(3, 2)))
                boulderId = boulder.getId();
        }

        assertTrue(player.getPosition().equals(new Position(2,2)));

        //Check if the player is next to the boulder
        //Then check if it is moved accordingly to the direction of the player that is moving        
        //Return true and move boulder if it is performed
        //Check also if boulder is next to a wall

        Direction playerMovment = Direction.RIGHT;
        newController.tick(null, playerMovment);
        boulders.clear();

        for (EntityResponse entity : newEntities){
            if (entity.getType().equals("player"))
                player = entity;
            else if (entity.getType().equals("boulder")){
                boulders.add(entity);
            }
        }

        assertTrue(player.getPosition().equals(new Position(3,2)));
        for (EntityResponse boulder : boulders){
            if (boulder.getId().equals(boulderId))
                assertTrue(boulder.getPosition().equals(new Position(4,2)));
        }     
    }    

    @Test
    public void testBoulderBlocked(){
        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse newResponse = newController.newGame("boulders", "Peaceful");
        List<EntityResponse> newEntities = newResponse.getEntities();

        EntityResponse player = null;
        ArrayList<EntityResponse> boulders = new ArrayList<>();
        for (EntityResponse entity : newEntities){
            if (entity.getType().equals("player"))
                player = entity;
            else if (entity.getType().equals("boulder")){
                boulders.add(entity);
            }
        }
        String boulderId = null;
        for (EntityResponse boulder : boulders){
            if (boulder.getPosition().equals(new Position(4, 4)))
                boulderId = boulder.getId();
        }

        assertTrue(player.getPosition().equals(new Position(2,2)));

        Direction playerMovment = Direction.RIGHT;
        newController.tick(null, playerMovment);
        newController.tick(null, playerMovment);
        newController.tick(null, playerMovment);
        playerMovment = Direction.DOWN;
        newController.tick(null, playerMovment);
        newController.tick(null, playerMovment);
        boulders.clear();

        for (EntityResponse entity : newEntities){
            if (entity.getType().equals("player"))
                player = entity;
            else if (entity.getType().equals("boulder")){
                boulders.add(entity);
            }
        }

        assertTrue(player.getPosition().equals(new Position(5,4)));
        for (EntityResponse boulder : boulders){
            if (boulder.getId().equals(boulderId))
                assertTrue(boulder.getPosition().equals(new Position(4,4)));
        }

    }
    
}



package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import java.util.List;
import dungeonmania.response.models.*;
import dungeonmania.util.*;

public class WallTest {

    @Test
    // no entities coexisting with wall
    // gameType: game that incl walls: advanced, maze, bombs, boulders
    public void testWallBasic(String gameType) {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame(gameType, "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

        boolean notCoexisting = true; 
        Position wallPosition = new Position(1,1); 
        String wallId = new String(); 

        // position of a wall 
        for (EntityResponse e: Entities) { 
            if (e.getType().equals("wall")) {
                wallPosition = e.getPosition(); 
                wallId = e.getId(); 
            }
        }
        // check if position of another entity is the same as wall 
        for (EntityResponse e: Entities) {
            if ((e.getId() != wallId)) {
                if (e.getPosition().equals(wallPosition)) {
                    notCoexisting = false; 
                }
            }
        }
        assertTrue(notCoexisting);
        return; 
    }

    // testWallBasic for each game mode -- is this necessary? 
    /** 
    @Test
    public void testWallBasicGame1() {
        String gameType = "advanced";
        testWallBasic("advanced"); 
    }
    @Test
    public void testWallBasicGame2() {
        String gameType = "bombs";
        testWallBasic("bombs"); 
    }
    @Test
    public void testWallBasicGame3() {
        String gameType = "boulders";
        testWallBasic("boulders"); 
    }
    @Test
    public void testWallBasicGame4() {
        String gameType = "maze";
        testWallBasic("maze"); 
    }
    */

    @Test
    public void testWallMovement() {
        return; 
    }
    // test that wall actually blocks an entity's movement 
    // eg entity at position where there is no wall
    // entity 'moves' to position where there is wall
    // entity remains in original position 
    
}

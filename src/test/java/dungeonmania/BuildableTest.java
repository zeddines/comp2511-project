package dungeonmania;
import dungeonmania.*;
import dungeonmania.entity.buildable.Bow;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
import dungeonmania.game.Game;
import dungeonmania.map.DungeonMap;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class BuildableTest {

    @Test
    public void testBow(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        
        Position player = new Position(1,1);

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), player));
         
        // Moving down 13 tiles
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);

        // Moving to the right 9 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Picking up the first arrow
        Controller.tick("player", Direction.RIGHT);

        // Picking up the second arrow
        Controller.tick("player", Direction.RIGHT);

        // Picking up the wood
        Controller.tick("player", Direction.RIGHT);

        // Build the bow
        DungeonResponse newResponse = Controller.build("bow");
        boolean bowBuilt = false;

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("bow")) {
                bowBuilt = true;
                break;
            }  
        }
        assertTrue(bowBuilt);        
    }

    @Test
    public void testShield(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        
        Position player = new Position(1,1);

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), player));
        
        // Moving down 13 tiles
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);

        // Moving to the right 9 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);

        // Picking up the first treasure
        Controller.tick("player", Direction.UP);

        // Picking up the second arrow
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Picking up two woods
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        
        // Build the shield
        DungeonResponse newResponse = Controller.build("shield");
        boolean shieldBuilt = false;

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("shield")) {
                shieldBuilt = true;
                break;
            }  
        }
        assertTrue(shieldBuilt);    
    }

    @Test
    public void testShieldTwo(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        
        Position player = new Position(1,1);

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), player));
        
        // Moving down 8 tiles
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        
        // Moving to the right 7 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Moving down a tile
        Controller.tick("player", Direction.DOWN);

        // Moving to the right 2 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Picking up the first key 
        Controller.tick("player", Direction.RIGHT);                 

        // Picking up two woods
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        
        // Build the shield
        DungeonResponse newResponse = Controller.build("shield");
        boolean shieldBuilt = false;

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("shield")) {
                shieldBuilt = true;
                break;
            }  
        }
        assertTrue(shieldBuilt);    
    }
    
}

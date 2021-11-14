package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import java.util.List;
import dungeonmania.response.models.*;
import dungeonmania.util.*;

public class MiscTest {
    @Test
    public void testDungeons() {
        assertTrue(DungeonManiaController.dungeons().size() > 0);
        assertTrue(DungeonManiaController.dungeons().contains("maze"));
    }

    @Test 
    public void testBoulder(){

        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse newResponse = newController.newGame("boulders", "Peaceful");
        List<EntityResponse>  newEntities = newResponse.getEntities();
        Position newPosition = new Position(3,2);
        boolean positionCorrect = false;
        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("boulder") && entity.getPosition().equals(newPosition)) {
                positionCorrect = true;
                break;
            }  
        }
        assertTrue(positionCorrect);
    }

    @Test
    public void testBomb(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced-2", "Standard");
        List<EntityResponse> Entities = Response.getEntities();
        
        Position player = new Position(1,1);
        Position bomb = new Position(13,4);
        Position boulder = new Position(7,7);
        Position switch1 = new Position(5,5);
        Position mercenary = new Position(3,5);

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), player));
        
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

        // Moving down 6 tiles
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);

        // Moving to the left 3 tiles
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);

        // Moving up 3 tiles
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);

        // Moving down 3 tiles
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);

        // Moving to the right 3 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Moving up 4 tiles
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);

        // Moving to the right 4 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Moving to the left 4 tiles
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);

        // Moving up 2 tiles
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);

        // Moving down 3 tiles
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);
        
        // Moving to the right 3 tiles to pick up bomb
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Moving to the left 4 tiles
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);

        // Moving up 1 tile
        Controller.tick("player", Direction.UP);

        // Moving to the left 3 tiles
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);

        // Moving down 1 tile
        Controller.tick("player", Direction.DOWN);

        // Moving to the left 2 tiles
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);

        // Moving down 1 tile
        Controller.tick("player", Direction.DOWN);

        //TODO: PLANT THE BOMB
        Controller.interact("bomb");

        // Moving up 1 tile
        Controller.tick("player", Direction.UP);

        // Moving to the right 3 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        
        // Moving up 1 tile
        Controller.tick("player", Direction.UP);

        // Moving to the right 3 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Moving up 2 tiles
        Controller.tick("player", Direction.UP);
        Controller.tick("player", Direction.UP);

        // Moving to the left 5 tiles
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT);
        Controller.tick("player", Direction.LEFT); 
        
        // Moving down 3 tile
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);


        DungeonResponse newResponse = Controller.tick("player", Direction.DOWN);
        boolean blownUp = false;

        //Bomb blows up killing the walls and mercenary

        assertTrue(blownUp);

    }

    


}
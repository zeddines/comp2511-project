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
        DungeonResponse Response = Controller.newGame("testBomb.json", "Standard");
        List<EntityResponse> Entities = Response.getEntities();

        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.DOWN);
        Controller.tick("player", Direction.DOWN);


        // Position player = new Position(1,1);
        // Position bomb = new Position(13,4);
        // Position boulder = new Position(7,7);
        // Position switch1 = new Position(5,5);
        //Position mercenary = new Position(3,5);

        // Entities.stream()
        //         .filter(e -> e.getType().equals("player"))
        //         .forEach(e -> assertEquals(e.getPosition(), player));
        
        // Moving to the right 9 tiles
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);

        // // Moving down 6 tiles
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);

        // // Moving to the left 3 tiles
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);

        // // Moving up 3 tiles
        // Controller.tick("player", Direction.UP);
        // Controller.tick("player", Direction.UP);
        // Controller.tick("player", Direction.UP);

        // // Moving down 3 tiles
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);

        // // Moving to the right 3 tiles
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);

        // // Moving up 4 tiles
        // Controller.tick("player", Direction.UP);
        // Controller.tick("player", Direction.UP);
        // Controller.tick("player", Direction.UP);
        // Controller.tick("player", Direction.UP);

        // // Moving to the right 4 tiles
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);

        // // Moving to the left 4 tiles
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);

        // // Moving up 2 tiles
        // Controller.tick("player", Direction.UP);
        // Controller.tick("player", Direction.UP);

        // // Moving down 3 tiles
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);
        
        // // Moving to the right 3 tiles to pick up bomb
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);

        // // Moving to the left 4 tiles
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);

        // // Moving up 1 tile
        // Controller.tick("player", Direction.UP);

        // // Moving to the left 3 tiles
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);

        // // Moving down 1 tile
        // Controller.tick("player", Direction.DOWN);

        // // Moving to the left 2 tiles
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);

        // // Moving down 1 tile
        // Controller.tick("player", Direction.DOWN);

        // //TODO: PLANT THE BOMB
        // Controller.interact("bomb");

        // // Moving up 1 tile
        // Controller.tick("player", Direction.UP);

        // // Moving to the right 3 tiles
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        
        // // Moving up 1 tile
        // Controller.tick("player", Direction.UP);

        // // Moving to the right 3 tiles
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);
        // Controller.tick("player", Direction.RIGHT);

        // // Moving up 2 tiles
        // Controller.tick("player", Direction.UP);
        // Controller.tick("player", Direction.UP);

        // // Moving to the left 5 tiles
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT);
        // Controller.tick("player", Direction.LEFT); 
        
        // // Moving down 3 tile
        // Controller.tick("player", Direction.DOWN);
        // Controller.tick("player", Direction.DOWN);


        DungeonResponse newResponse = Controller.tick("player", Direction.DOWN);
        boolean blownUp = false;

        //Bomb blows up killing the walls and mercenary

        assertTrue(blownUp);

    }
    //mercenaries are bribed with two treasure
    //use the interact()
    //test exceptions thrown for interact method, it's hard to test whether the character has bribed the npc or not  
    //we can't really test how much damage the players received, we only know whehter it is dead or not
    //so when test battle, make the player face a lot of enemies, and guarantee dungeon response does not contain player
    //if you don't know how to do then just tidy up the documents

    @Test
    public void testBribeMercenary(){
        
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("testMercenary.json", "Standard");
        List<EntityResponse> Entities = Response.getEntities();

        //Collect two treasures
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        //Bribe mercenary
        Controller.tick("player", Direction.RIGHT);
        Controller.interact("mercenary");

        boolean bribed = false;

        //TODO: Ensure Mercenary is bribed
        assertTrue(bribed);

    }

    @Test
    public void attackMercenary(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("testMercenary.json", "Standard");
        List<EntityResponse> Entities = Response.getEntities();

        //Move to the right
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        //Attack mercenary
        Controller.tick("player", Direction.RIGHT);

        boolean killed = false;

        //TODO: Ensure Mercenary is killed
        assertTrue(killed);

    }

    @Test
    public void testBribeAssassin(){
        
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("testMercenary.json", "Standard");
        List<EntityResponse> Entities = Response.getEntities();

        //Collect two treasures
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        //Bribe mercenary
        Controller.tick("player", Direction.RIGHT);
        Controller.interact("assassin");

        boolean bribed = false;

        //TODO: Ensure Mercenary is bribed
        assertTrue(bribed);
    }


}
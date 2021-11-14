package dungeonmania;
import dungeonmania.*;
import dungeonmania.entity.buildable.Bow;
import dungeonmania.entity.buildable.Sceptre;
import dungeonmania.entity.buildable.Shield;
import dungeonmania.entity.collectable.Arrow;
import dungeonmania.entity.collectable.Key;
import dungeonmania.entity.collectable.SunStone;
import dungeonmania.entity.collectable.Treasure;
import dungeonmania.entity.collectable.Wood;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
import dungeonmania.entityfactory.BuildableFactory;
import dungeonmania.game.Game;
import dungeonmania.map.DungeonMap;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
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
        DungeonResponse Response = Controller.newGame("advanced-2", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        List<ItemResponse> Inventory = Response.getInventory();
        
        Player player = new Player(new Position(1, 1), "Peaceful", getMap());
        Arrow arrow = new Arrow(new Position(11,13), "Peaceful", getMap());
        Arrow arrow2 = new Arrow(new Position(11,14), "Peaceful", getMap());
        Arrow arrow3 = new Arrow(new Position(12,14), "Peaceful", getMap());
        Wood wood = new Wood(new Position(11,11), "Peaceful", getMap());        

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
            if (entity.getType().equals("bow") && Inventory.contains(bow)) {
                bowBuilt = true;
                break;
            }  
        }
        assertTrue(bowBuilt);        
    }

    @Test
    public void testShield(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced-2", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        List<ItemResponse> Inventory = Response.getInventory();
        
        Player player = new Player(new Position(1, 1), "Peaceful", getMap());
        Key key = new Key(new Position(11, 10), "Peaceful", key, getMap());
        Key key2 = new Key(new Position(12, 10), "Peaceful", key2, getMap());
        Treasure treasure = new Treasure(new Position(7, 10), "Peaceful", getMap());
        Wood wood = new Wood(new Position(11,11), "Peaceful", getMap());
        Wood wood2 = new Wood(new Position(11,12), "Peaceful", getMap());

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

        // Moving to the right 6 tiles
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
        Inventory.add(treasure);

        // Picking up the second arrow
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Inventory.add(key);

        // Picking up two woods
        Controller.tick("player", Direction.DOWN);
        Inventory.add(wood);
        Controller.tick("player", Direction.DOWN);
        Inventory.add(wood2);
        
        // Build the shield 
        Shield shield = new Shield(new Position(11, 12), getMap, player, 10);       
        Controller.build(shield);
        DungeonResponse newResponse = Inventory.add(shield);
        boolean shieldBuilt = false;

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("shield") && Inventory.contains(shield)) {
                shieldBuilt = true;
                break;
            }  
        }
        assertTrue(shieldBuilt);    
    }

    @Test
    public void testShieldTwo(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced-2", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        List<ItemResponse> Inventory = Response.getInventory();
        
        Player player = new Player(new Position(1, 1), "Peaceful", getMap());
        Key key = new Key(new Position(11, 10), "Peaceful", key, getMap());
        Key key2 = new Key(new Position(12, 10), "Peaceful", key2, getMap());
        Treasure treasure = new Treasure(new Position(7, 10), "Peaceful", getMap());
        Wood wood = new Wood(new Position(11,11), "Peaceful", getMap());
        Wood wood2 = new Wood(new Position(11,12), "Peaceful", getMap());


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
        Inventory.add(key);
        

        // Picking up two woods
        Controller.tick("player", Direction.DOWN);        
        Inventory.add(wood);
        Controller.tick("player", Direction.DOWN);
        Inventory.add(wood2);
        
        // Build the shield
        Shield shield = new Shield(new Position(11, 12), getMap, player, 10);       
        Controller.build(shield);
        DungeonResponse newResponse = Inventory.add(shield);
        boolean shieldBuilt = false;

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("shield") && Inventory.contains(shield)) {
                shieldBuilt = true;
                break;
            }  
        }
        assertTrue(shieldBuilt);    
    }

    @Test
    private void testBuildBoth(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced-2", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        List<ItemResponse> Inventory = Response.getInventory();
        
        Player player = new Player(new Position(1, 1), "Peaceful", getMap());
        Key key = new Key(new Position(11, 10), "Peaceful", key, getMap());
        Key key2 = new Key(new Position(12, 10), "Peaceful", key2, getMap());
        Treasure treasure = new Treasure(new Position(7, 10), "Peaceful", getMap());
        Wood wood = new Wood(new Position(11,11), "Peaceful", getMap());
        Wood wood2 = new Wood(new Position(11,12), "Peaceful", getMap());

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
        Inventory.add(key);


        // Picking up two woods
        Controller.tick("player", Direction.DOWN);        
        Inventory.add(wood);
        Controller.tick("player", Direction.DOWN);
        Inventory.add(wood2);

        // Build the shield
        Shield shield = new Shield(new Position(11, 12), getMap(), player, 10);     
        Bow bow = new Bow(new Position(11, 12), getMap(), player, 10);      
        Controller.build(shield);
        Controller.build(bow);
        Inventory.add(shield);
        DungeonResponse newResponse = Inventory.add(bow);
        boolean shieldBuilt = false;
        boolean bowBuilt = false;

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("shield") && Inventory.contains(shield) && entity.getType().equals("bow") && Inventory.contains(bow)) {
                shieldBuilt = true;
                bowBuilt = true;
                break;
            }  
        }
        assertTrue(shieldBuilt);   
        assertTrue(bowBuilt);  
    }

    @Test
    public void testBuildSceptre(){
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("testSceptreBuild", "Peaceful");
        List<EntityResponse> Entities = Response.getEntities();
        List<ItemResponse> Inventory = Response.getInventory();
        
        Player player = new Player(new Position(1, 1), "Peaceful", getMap());
        Key key = new Key(new Position(4, 1), "Peaceful", key, getMap());
        SunStone sunStone = new SunStone(new Position(2, 1), "Peaceful", getMap());        
        Wood wood = new Wood(new Position(3,1), "Peaceful", getMap());
        
        Entities.stream()
        .filter(e -> e.getType().equals("player"))
        .forEach(e -> assertEquals(e.getPosition(), player));

        // Picking up the first key 
        Controller.tick("player", Direction.RIGHT); 
        Inventory.add(sunStone);
        Controller.tick("player", Direction.RIGHT); 
        Inventory.add(wood);
        Controller.tick("player", Direction.RIGHT); 
        Inventory.add(key);

        Sceptre sceptre = new Sceptre(game, "Peaceful", player);
        DungeonResponse newResponse = Inventory.add(sceptre);
        boolean sceptreBuilt = false;

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("sceptre") && Inventory.contains(sceptre)) {
                sceptreBuilt = true;                
                break;
            }  
        }
        assertTrue(sceptreBuilt); 
    }
    
}

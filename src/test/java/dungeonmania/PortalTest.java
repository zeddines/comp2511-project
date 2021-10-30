package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import java.util.List;
import dungeonmania.response.models.*;
import dungeonmania.util.*;

public class PortalTest {

    @BeforeEach
    // see how this works 
    void dungeonSetUp() {
        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse newResponse = newController.newGame("portals", "Peaceful");
        List<EntityResponse>  newEntities = newResponse.getEntities();
    }

    @Test 
    // basic portal enter and exit: enter a portal, exit corresponding portal 
    // pass in entity type eg spider, mercenary, player 

    // is there a better way to test movement -- TO DO 
    // instead of 'refreshing' getEntities? 
    public boolean testPortalBasic(String entity) {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("portals", "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

        Position portal1 = new Position(1,1);
        Position portal2 = new Position(2,2);

        // position of portals 
        for (EntityResponse p1: Entities) {
            if (p1.getType().equals("portal")) {
                // position of portal 1 
                portal1 = p1.getPosition(); 
                Entities.remove(p1); 
                for (EntityResponse p2: Entities) {
                    if (p2.getType().equals("portal")) {
                        // position of portal 2 
                        portal2 = p2.getPosition(); 
                    }
                }
            }
        }
        // entity steps into portal 
        for (EntityResponse e: Entities) {
            if (e.getType().equals(entity)) {
                if (e.getPosition().equals(portal1)) {
                    // refresh 
                    Entities = Response.getEntities();
                }
            }
        }
        boolean hasMoved = false; 
        // entity emerges from other portal 
        for (EntityResponse e: Entities) {
            if (e.getType().equals(entity)) {
                if (e.getPosition().equals(portal2)) {
                    hasMoved = true; 
                }
            }
        }
        assertTrue(hasMoved); 
        return hasMoved; 
    }

    @Test 
    // enter a portal, exit corresponding portal in same direction -- TO DO 
    public void testPortalDirection() {
        // same as above but with direction ie modify xy coords 
    }

    @Test 
    // check if 2 portals in the same position 
    public void testPortalPosition() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("portals", "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

        boolean notDuplicated = true; 
        Position portal1;
        Position portal2;
        String portal1Id = new String(); 

        for (EntityResponse e1: Entities) {
            if (e1.getType().equals("portals")) {
                // position of portal 1 
                portal1 = e1.getPosition(); 
                portal1Id = e1.getId(); 
                for (EntityResponse e2: Entities) {
                    if ((e2.getId() != portal1Id) && (e2.getType().equals("portal"))) {
                        // position of portal 2 
                        portal2 = e2.getPosition(); 
                        if (portal1.equals(portal2)) {
                            notDuplicated = false; 
                        }
                    }
                }
            }
        }
        assertTrue(notDuplicated); 
    }

    @Test
    // only 2 portals -- assumption? 
    public void testNumPortal() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("portals", "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

        int numPortals = 0; 
        for (EntityResponse e: Entities) {
            if (e.getType().equals("portal")) {
                numPortals++; 
            }
        }
        assertEquals(2, numPortals); 
    }

    @Test
    // player can move through portal -- TO DO 
    public void testPlayerPortal() {
        return; 
    }

    @Test
    // spiders can move through portals 
    public void testSpiderPortal() {
        boolean canMove = true; 
        assertEquals(canMove, testPortalBasic("spider")); 
    }

    @Test
    // mercenaries can move through portals 
    public void testMercPortal() {
        boolean canMove = true; 
        assertEquals(canMove, testPortalBasic("mercenary")); 
    }

    @Test
    // zombie cannot move through portal 
    public void testNoZombiePortal() {
        boolean canMove = false; 
        assertEquals(canMove, testPortalBasic("zombie_toast"));
    }

    @Test
    // zombie stepping on portal has no effect 
    public void testZombiePortal() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("portals", "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

        Position portal1 = new Position(1,1);

        for (EntityResponse p: Entities) {
            if (p.getType().equals("portal")) {
                // position of portal 1 
                portal1 = p.getPosition(); 
            }
        }
        // zombie steps on portal 
        String zombieId = new String(); 
        for (EntityResponse e: Entities) {
            if (e.getType().equals("zombie_toast")) {
                zombieId = e.getId();
                if (e.getPosition().equals(portal1)) {
                    Entities = Response.getEntities();
                }
            }
        }
        // check zombie position unchanged 
        for (EntityResponse e: Entities) {
            if (e.getId().equals(zombieId)) {
                assertEquals(portal1, e.getPosition()); 
            }
        }
    }
}

package dungeonmania;

import dungeonmania.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Door;
import dungeonmania.game.Game;
import dungeonmania.map.DungeonMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;

import java.util.ArrayList;
import java.util.List;
import dungeonmania.response.models.*;
import dungeonmania.util.*;


public class DoorTest {
    @Test
    public void testDoorNoKey() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Standard");
        List<EntityResponse>  Entities = Response.getEntities();

        Position player = new Position(1,1);
        Position door = new Position(2, 1);

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

        // Moving right 3 tiles
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);
        Controller.tick("player", Direction.RIGHT);

        // Attempting to unlock by moving down        
        DungeonResponse newResponse = Controller.tick("player", Direction.DOWN);
        boolean unlocked = false;
        ArrayList<EntityResponse> keys = new ArrayList<>();

        List<EntityResponse> newEntities = newResponse.getEntities();

        for (EntityResponse entity: newEntities) {
            if (entity.getType().equals("door")) {
                if(keys.contains("keys")){
                    unlocked = true;
                    break;
                }                
            }  
        }
        assertFalse(unlocked);                
    }
}

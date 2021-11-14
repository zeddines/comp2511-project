package dungeonmania;

import dungeonmania.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Door;
import dungeonmania.game.Game;
import dungeonmania.map.DungeonMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import java.util.List;
import dungeonmania.response.models.*;
import dungeonmania.util.*;


public class DoorTest {
    @Test
    public void testDoorNoKey() {
        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse Response = newController.newGame("advanced", "Standard");
        List<EntityResponse>  Entities = Response.getEntities();

        Position original = new Position(1,1);
        Position oneStep = new Position(2, 1);

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), original));

        DungeonResponse newResponse = newController.tick("player", Direction.RIGHT);

        List<EntityResponse> newEntities = newResponse.getEntities();

        newEntities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), oneStep));
        
        
    }


}

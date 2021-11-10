package dungeonmania;

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
    // door acts like wall (no key)
    public void testDoorNoKey() {
        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse newResponse = newController.newGame("advanced-2", "Peaceful");
        List<EntityResponse>  newEntities = newResponse.getEntities();

        Position newDoor = new Position(15,9);

        newEntities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), original));


        DungeonResponse Response = newController.tick("player", Direction.RIGHT);
        

    }

    // test for door-key interaction can go in items tests

}

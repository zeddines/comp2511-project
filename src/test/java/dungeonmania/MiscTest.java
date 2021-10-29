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
}
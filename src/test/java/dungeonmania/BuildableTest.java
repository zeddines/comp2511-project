package dungeonmania;
import dungeonmania.*;
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
        List<EntityResponse>  Entities = Response.getEntities();
        
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

        List<EntityResponse> newEntities = newResponse.getEntities();

        newEntities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), twoStep));
        
        
        
    }
    
}

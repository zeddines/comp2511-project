package dungeonmania;

import dungeonmania.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
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


public class MovementTest {
    @Test
    public void simpleMove() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

//        for (EntityResponse e: Entities) {
//            if (e.getType().equals("player")) {
//                System.out.println(e.getPosition().toString());
//            }
//        }

        Position original = new Position(1,1);
        Position oneStep = new Position(2, 1);

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), original));

        DungeonResponse newResponse = Controller.tick(null, Direction.RIGHT);

        List<EntityResponse> newEntities = newResponse.getEntities();

        newEntities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), oneStep));
    }

    @Test
    public void twoStepMove() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

        Position original = new Position(1,1);
        Position twoStep = new Position(2,2);

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), original));

        Controller.tick(null, Direction.DOWN);
        DungeonResponse newResponse = Controller.tick(null, Direction.RIGHT);

        List<EntityResponse> newEntities = newResponse.getEntities();

        newEntities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), twoStep));
    }

    @Test
    public void backStepMove() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Peaceful");
        List<EntityResponse>  Entities = Response.getEntities();

        Position original = new Position(1,1);
        Position twoStep = new Position(1, 3);

        Entities.stream()
                .filter(e -> e.getType().equals(null))
                .forEach(e -> assertEquals(e.getPosition(), original));

        Controller.tick(null, Direction.DOWN);
        Controller.tick(null, Direction.RIGHT);
        Controller.tick(null, Direction.LEFT);
        DungeonResponse newResponse = Controller.tick(null, Direction.DOWN);

        List<EntityResponse> newEntities = newResponse.getEntities();

        newEntities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), twoStep));
    }

    @Test
    public void itemPickAndMove() {
        DungeonManiaController Controller = new DungeonManiaController();
        DungeonResponse Response = Controller.newGame("advanced", "Peaceful");

        Position itemLocation = new Position(6,1);

        for (int i = 0; i < 5; i++) {
            Response = Controller.tick(null, Direction.RIGHT);
        }

        List<EntityResponse> Entities = Response.getEntities();

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), itemLocation));

        Controller.tick(null, Direction.RIGHT);
        Response = Controller.tick(null, Direction.LEFT);
        Entities = Response.getEntities();

        Entities.stream()
                .filter(e -> e.getType().equals("player"))
                .forEach(e -> assertEquals(e.getPosition(), itemLocation));
    }






































}

package dungeonmania;
import dungeonmania.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.square.Boulder;
import dungeonmania.game.Game;
import dungeonmania.map.DungeonMap;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;


public class BoulderTest {
        
    @Test 
    public void testBoulder(){  
        DungeonManiaController newController = new DungeonManiaController();
        DungeonResponse newResponse = newController.newGame("boulders", "Peaceful");
        List<EntityResponse>  newEntities = newResponse.getEntities();

        //Get the positions of every single boulder
        Position newPosition = new Position(3,2);
        Position newPosition2 = new Position(4,3);
        Position newPosition3 = new Position(4,4);
        Position newPosition4 = new Position(1,6);
        Position newPosition5 = new Position(3,6);
        Position newPosition6 = new Position(4,6);
        Position newPosition7 = new Position(5,6);

        //Make a list to store all the boulder positions
        List<Position> boulderPositionList = new ArrayList<>();  
        boulderPositionList.add(newPosition);
        boulderPositionList.add(newPosition2);
        boulderPositionList.add(newPosition3);
        boulderPositionList.add(newPosition4);
        boulderPositionList.add(newPosition5);
        boulderPositionList.add(newPosition6);
        boulderPositionList.add(newPosition7);

        boolean positionCorrect = false;
        for (EntityResponse entity: newEntities) {            
            if (entity.getType().equals("boulder")) {
                if (boulderPositionList.contains(entity.getPosition())){
                    boulderPositionList.remove(entity.getPosition());                                                                
                }
                else {
                    break;
                }
            }              
        }

        if (boulderPositionList.isEmpty()){
            positionCorrect = true;
        }

        assertTrue(positionCorrect);
    }                

}



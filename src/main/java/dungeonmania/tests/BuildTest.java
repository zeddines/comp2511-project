package dungeonmania.tests;

import dungeonmania.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Tests bows and shields to be built
 */

public class BuildTest {
    @Test
    void testBuildBow(){
        Dungeon dungeon = new DungeonMap(10,10);        
        Wood wood = new Wood(dungeon, 0, 1, 1, new Collectable());
        Arrow arrow1 = new Arrow(dungeon, 1, 1, 1, new Collectable());
        Arrow arrow2 = new Arrow(dungeon, 1, 0, 1, new Collectable());
        Arrow arrow3 = new Arrow(dungeon, 0, 1, 1, new Collectable());

        player.moveLeft();

        inventory.add(wood);
        inventory.add(arrow1);
        inventory.add(arrow2);
        inventory.add(arrow3);

        assertEquals


    }

    @Test
    void testBuildShield(){
        Dungeon dungeon = new DungeonMap(10,10);        
        Wood wood1 = new Wood(dungeon, 0, 1, 1, new Collectable());
        Wood wood2 = new Wood(dungeon, 1, 1, 1, new Collectable());
        Treasure treasure = new Treasure(dungeon, 1, 2, 1, new Collectable());      
        
        player.moveLeft();

        inventory.add(wood1);
        inventory.add(wood2);
        inventory.add(treasure);
        


    }
}

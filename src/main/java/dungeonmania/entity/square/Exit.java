package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class Exit extends Entity {
    public Exit(Position currentPosition) {
        super(currentPosition); 
    }
    
    /**
     *  Puzzle is complete if character goes through it 
     */
    @Override
    public boolean action (EntityAPI creature) {
        return false;
    }
}

package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class FloorSwitch extends Entity {
    private boolean triggered; 

    public FloorSwitch(Position currentPosition) {
        super(currentPosition); 
        this.triggered = false; 
    }

    /**
     *  Switches behave like empty squares - other entities can appear on top of them
     *  When a boulder is pushed onto a floor switch, it is triggered 
     *  Pushing a boulder off the floor switch untriggers it 
     */
    @Override
    public boolean action(EntityAPI creature) {
        return false;
    }
    
}

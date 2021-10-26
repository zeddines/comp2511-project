package dungeonmania.entity.square;

public class FloorSwitch extends Square {
    private boolean triggered; 

    public FloorSwitch(int x, int y) {
        super(x,y); 
        this.triggered = false; 
    }

    /**
     *  Switches behave like empty squares - other entities can appear on top of them
     *  When a boulder is pushed onto a floor switch, it is triggered 
     *  Pushing a boulder off the floor switch untriggers it 
     */
    @Override
    public void action(String s) {
        return; 
    }
    
}

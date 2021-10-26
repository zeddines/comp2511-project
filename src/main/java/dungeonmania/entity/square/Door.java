package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class Door extends Entity {
    private String key; 
    private boolean opened; 

    public Door(int x, int y) {
        super(x,y); 
        this.opened = false; 
    }

    /**
     *  Exists in conjunction with a single key that can open it 
     *  If character holds the key, they can open the door by moving through it
     *  Remains open once opened 
     */
    @Override
    public void action(String s) {
        return; 
    }
    
}

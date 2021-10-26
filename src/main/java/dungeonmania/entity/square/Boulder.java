package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class Boulder extends Wall {
    public Boulder(int x, int y) {
        super(x,y); 
    }

    /**
     *  Boulder acts like a wall in most cases. 
     *  Can be pushed by character into cardinally adjacent squares
     *  Character can only push one boulder at a time 
     */
    @Override
    public void action(String s) {
        return; 
    }
}

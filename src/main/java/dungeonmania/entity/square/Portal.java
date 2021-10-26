package dungeonmania.entity.square;

public class Portal extends Square {
    public Portal(int x, int y) {

        // corresponding portal 

        super(x,y); 
    }

    /**
     *  Teleports entities to a corresponding portal 
     */
    @Override
    public void movement(String s) {
        return; 
    }
    
}

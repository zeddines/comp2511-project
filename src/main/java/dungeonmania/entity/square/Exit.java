package dungeonmania.entity.square;

public class Exit extends Square {
    public Exit(int x, int y) {
        super(x,y); 
    }
    
    /**
     *  Puzzle is complete if character goes through it 
     */
    @Override
    public void action(String s) {
        return; 
    }
}

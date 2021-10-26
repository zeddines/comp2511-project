package dungeonmania.entity.square;

public class Wall extends Square {
    public Wall(int x, int y) {
        super(x,y); 
    }

    @Override
    public void action(String s) {
        return; 
    }

    /**
     *  Blocks the movement of the character, enemies and boulders 
     */
    @Override
    public void movement(String s) {
        return; 
    }
}

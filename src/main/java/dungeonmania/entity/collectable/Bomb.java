package dungeonmania.entity.collectable;
import dungeonmania.entity.*;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Bomb extends Collectable {
    /**
     * can be collected by char
     * when char places a bomb cardinally adjacent to a switch
     * if boulder is pushed onto the switch then the bomb explodes
     * destroying all entieis in the bomb's blast radius except player 
     */
    public Bomb() {
        super(); 
    }

    public void use() {
        return; 
    }
}

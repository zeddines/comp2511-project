package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Exit extends Entity {    
    private boolean playerIsOnTop;
    /**
     *  Puzzle is complete if character goes through it 
     */
    public Exit(Position current, String type, DungeonMapAPI map) {
        super(map, current, type);
        playerIsOnTop = false;
    }

    public boolean getPlayerIsOnTop(){
        return playerIsOnTop;
    }

    @Override
    public void leaveAction(Player player){
        playerIsOnTop = false;
    }

    @Override
    public void collideAction(Player player){
        playerIsOnTop = true;
    }
    
}

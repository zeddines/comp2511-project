package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class Boulder extends Entity{

    /**
     *  Boulder acts like a wall in most cases. 
     *  Can be pushed by character into cardinally adjacent squares
     *  Character can only push one boulder at a time 
     */
    public Boulder(Position current, String type, DungeonMapAPI map) {
        //you sure you want boulder to have interactable to be true? naming confusion will arise when calling interact method.
        super(map, current, type);
    }

    public boolean canMove(Direction direction){
        if (getGame().canBeInPosition(this, getPosition().translateBy(direction))){
            return true;
        }
        return false;
    }

    @Override
    public boolean canBeOnSamePosition(Enemy enemy){
        return false;
    }

    @Override
    public boolean canBeOnSamePosition(Boulder boulder){
        return false;
    }
}

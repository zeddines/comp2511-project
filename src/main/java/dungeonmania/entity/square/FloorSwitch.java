package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Bomb;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class FloorSwitch extends Entity {
    private boolean triggered; 
    /**
     *  Switches behave like empty squares - other entities can appear on top of them
     *  When a boulder is pushed onto a floor switch, it is triggered 
     *  Pushing a boulder off the floor switch untriggers it 
     */
    public FloorSwitch(Position current, String type, DungeonMapAPI map) {
       super(map, current, type);
        this.triggered = false;
    }

    @Override
    public boolean canBeOnSamePosition(Boulder boulder){
        return true;
    }

    @Override
    public void collideAction(Boulder boulder){
        triggered = true;
        for (EntityAPI entity : getGame().getAllEntitiesInMap()){
            if (entity instanceof Bomb && Position.isAdjacent(entity.getPosition(), getPosition())){
                ((Bomb)entity).detonate();
            }
        }
    }

    @Override
    public void leaveAction(Boulder boulder){
        triggered = false;
    }
}

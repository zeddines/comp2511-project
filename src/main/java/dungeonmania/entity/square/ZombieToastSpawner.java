package dungeonmania.entity.square;
import dungeonmania.entity.*;
import dungeonmania.entity.collectable.Armour;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.creature.NonInteractableEnemy;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.Interactable;
import dungeonmania.entity.interfaces.MovableNPC;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;
import org.json.JSONObject;

public class ZombieToastSpawner extends Entity implements Interactable{
    private int coolDown;
    private int roundsLeft;
    /**
     *  Spawns zombie toasts every 20 ticks in an open square cardinally adjacent to the spawner 
     *  Character can destroy a zombie spawner if they have a weapon and are cardinally adjacent 
     *  to spawner 
     */
    //can input coolDown as argument constructor, can be done in factory
    public ZombieToastSpawner(Position current, String type, DungeonMapAPI map) {
        super(map, current, type);
        this.coolDown = 20;
        this.roundsLeft = coolDown;
    }
    @Override
    public void interact() throws InvalidActionException{
        Player player = getGame().getPlayer();
        if (!Position.isAdjacent(player.getPosition(), getPosition()))
            throw new InvalidActionException("not cardinally adjacent to the spawner");
        for (BattleGear battleGear :  DungeonMap.shallowClone(player.getBattleGears())){
            if (battleGear.isWeapon()){
                battleGear.reduceDurability();
                player.getBattleStat().removeAllDeteriorated();
                getGame().removeEntity(this);
                return;
            }
        }
        throw new InvalidActionException("player does not have a weapon and attempts to destroy a spawner");
    }
}

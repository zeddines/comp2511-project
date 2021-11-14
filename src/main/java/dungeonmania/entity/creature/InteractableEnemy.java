package dungeonmania.entity.creature;
import java.util.ArrayList;
import java.util.List;

import dungeonmania.entity.buildable.Sceptre;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.interfaces.Interactable;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.*;

public class InteractableEnemy extends Enemy implements Interactable{
    List<List<String>> itemsRequiredToBribe;
    /**
     * On maps with at least one enemy, mercenaries spawn at the entry location periodically
     * They constrantly move towarsd the character, stopping if they cannot move any closer 
     * Mercenaries are limited by same movement constraints as the character
     * All mecenaries are considered hostile, unless the character can bribe them with a certain amount of gold 
     * in which case they become allies 
     * As an ally, once it reaches the player it simply follows the player around 
     */

     // please set battleStat and movement as input to constructor( movment rn is null)
    public InteractableEnemy(Position current, String type, DungeonMapAPI map) {
        super(map, type, current);
        itemsRequiredToBribe = new ArrayList<List<String>>();
    }

    @Override
    public void interact() throws InvalidActionException{
        if (getGame().getAllies().contains(this))
            throw new InvalidActionException("trying to bribe an ally");
        Player player = getGame().getPlayer();
        if (Position.calculateCardinalDistanceBetween(player.getPosition(), getPosition()) > 2)
            throw new InvalidActionException("player is not within 2 cardinal tiles to " + getType());
        for (Collectable collectable : player.getNonBattleItems()){
            if (collectable instanceof Sceptre){
                ((Sceptre)collectable).mindControl(this);
                return;
            }
        }
        for (List<String> bribeList : itemsRequiredToBribe){
            ArrayList<Collectable> collectablesReceived = player.give(bribeList);
            if (collectablesReceived != null){
                getGame().addToAlly(this);
                return;
            }
        }
        throw new InvalidActionException("does not have items to bribe");
    }

    public void addItemsRequiredToBribe(List<String> itemsRequiredToBribe) {
        this.itemsRequiredToBribe.add(itemsRequiredToBribe);
    }
}

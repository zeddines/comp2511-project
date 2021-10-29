package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.Entity;
import dungeonmania.entity.interfaces.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.util.Position;
import dungeonmania.map.DungeonMapAPI;

public class Collectable extends Entity implements CollideActionEntity {
    private Creature owner = null;

    //use this when creating a collectable with an owner. does not have a position
    public Collectable(DungeonMapAPI game, String type, boolean isInteractable, Creature owner) {
        super(game, null, type, isInteractable);
        this.owner = owner;
    }
    
    //use this when creating a collectable at ground
    public Collectable(DungeonMapAPI game, String type, Position position, boolean isInteractable) {
        super(game, position, type, isInteractable);
    }

    public void collideAction(Player player){
        super.collideAction(player);
        player.addCollectable(this);
        //TODO NOT MENTIONED IN UML
        //game.removeEntityFromMap(this);
        //setPosition(null);
    }

    public void removeFromInventory(){
        owner.removeCollectable(this);
    }

    //getter setter
    
    public Creature getOwner() {
        return owner;
    }

    public void setOwner(Creature owner) {
        this.owner = owner;
    }
}

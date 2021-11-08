package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.Entity;
import dungeonmania.entity.interfaces.*;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.util.Position;
import dungeonmania.map.DungeonMapAPI;

public class Collectable extends Entity{
    private Creature owner = null;

    //use this when creating a collectable with an owner. does not have a position
    public Collectable(DungeonMapAPI game, String type, Creature owner) {
        super(game, null, type);
        this.owner = owner;
    }
    
    //use this when creating a collectable at ground
    public Collectable(DungeonMapAPI game, String type, Position position) {
        super(game, position, type);
    }

    @Override
    public void collideAction(Player player){
        getGame().removeEntity(this);
        
        player.addCollectable(this);
        setPosition(null);
        owner = player;
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

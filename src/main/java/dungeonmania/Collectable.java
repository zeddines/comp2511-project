package dungeonmania;

import dungeonmania.creatures.Player;
import dungeonmania.util.Position;

public class Collectable extends Entity implements CollideActionEntity {
    private Creature owner = null;

    //use this when creating a collectable with an owner. does not have a position
    public Collectable(DungeonManiaController game,String id, String type, boolean isInteractable, Creature owner) {
        super(game, id, type, null, isInteractable);
        this.owner = owner;
    }
    
    //use this when creating a collectable at ground
    public Collectable(DungeonManiaController game, String id, String type, Position position, boolean isInteractable) {
        super(game, id, type, position, isInteractable);
    }

    public void collideAction(Player player){
        player.addCollectable(this);
        setPosition(null);
        //TODO NOT MENTIONED IN UML
        getGame().removeEntityFromMap(this);
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

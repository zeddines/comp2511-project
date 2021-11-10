package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.Usable;
import dungeonmania.util.Position;

public class Bomb extends Collectable implements Usable{
    private boolean hasBeenDeployed;
    private int radius;

    public Bomb(Position current, String type, DungeonMapAPI map) {
        super(map, type, current);
        hasBeenDeployed = false;
        this.radius = 2;
    }

    @Override
    public void use() {
        hasBeenDeployed = true;
        removeFromInventory();
        setPosition(getOwner().getPosition());
        getGame().addEntity(this);
        setOwner(null);
    }

    @Override
    public void collideAction(Player player){
        if (!hasBeenDeployed)
            super.collideAction(player);
    }

    public void detonate(){
        if (hasBeenDeployed){
            for (EntityAPI entity : getGame().getAllEntitiesInMap()){
                if (Position.isInRadius(getPosition(), entity.getPosition(), radius) && !"player".equals(entity.getType()))
                    getGame().removeEntity(entity);
            }
        }
    }
}

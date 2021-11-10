package dungeonmania.entity.collectable;

import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.Usable;
import dungeonmania.util.Position;


//TODO NOT MENTIONED IN UML
public class Potion extends Collectable implements Usable{
    Effect effect;


    public Potion(Position position, String type, DungeonMapAPI game, Effect effect) {
        super(game, type, position);
        this.effect = effect;
    }

    @Override
    public void collideAction(Player player){
        super.collideAction(player);
        effect.setTarget(player);
    }

    @Override
    public void use() {
        removeFromInventory();
        effect.apply();
    }
}

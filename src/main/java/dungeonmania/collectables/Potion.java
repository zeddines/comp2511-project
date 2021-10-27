package dungeonmania.collectables;

import dungeonmania.Collectable;
import dungeonmania.Creature;
import dungeonmania.DungeonManiaController;
import dungeonmania.Usable;
import dungeonmania.creatures.Player;
import dungeonmania.util.Position;

//TODO NOT MENTIONED IN UML
public class Potion extends Collectable implements Usable{
    PotionEffect effect;

    public Potion(DungeonManiaController game, String id, String type, Position position,
            boolean isInteractable, int buffLastFor, PotionEffect effect) {
        super(game, id, type, position, isInteractable);
        this.effect = effect;
    }

    @Override
    public void use() {
        removeFromInventory();
        ((Player)getOwner()).addPotionInEffect(this);
    }

    // public TemporaryBuffItem(DungeonManiaController game, String id, String type, boolean isInteractable,
    //         Creature owner) {
    //     super(game, id, type, isInteractable, owner);
    // }
}

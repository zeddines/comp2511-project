package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMap;
import dungeonmania.map.DungeonMapAPI;

public class InvincibilityEffect extends Effect{
    
    public InvincibilityEffect(int lastFor, DungeonMapAPI game) {
        super(lastFor, game);
    }

    @Override
    public void applyEffect() {
        getPlayer().applyInvincibleBuff();
        //TODO SET ALL ENEMIES FLEEING MOVEMENT BEHAVIOUR
    }

    @Override
    public void endEffect() {
        getPlayer().revertInvincibleBuff();
        //set all enemies back to default
    }
}

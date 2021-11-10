package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.map.DungeonMapAPI;

public class InvincibilityEffect extends Effect{
    
    public InvincibilityEffect(Creature target, int lastFor, DungeonMapAPI game) {
        super(target, lastFor, game);
    }

    @Override
    public void applyEffect() {
        getTarget().applyInvincibleBuff();
        //TODO SET ALL ENEMIES FLEEING MOVEMENT BEHAVIOUR
    }

    @Override
    public void endEffect() {
        getTarget().revertInvincibleBuff();
        //set all enemies back to default
    }
}

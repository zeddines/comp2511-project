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

        //SHOULD CALL SOME FUNCTION IN MAPDUNGEON TO SET ALL ENEMY MOVEMENT TO FLEE
    }

    @Override
    public void endEffect() {
        getTarget().revertInvincibleBuff();
        //TODO SET ALL ENEMIES BACK TO DEFAULT BEHAVIOUR

        //SHOULD CALL SOME FUNCTION IN MAPDUNGEON TO SET ALL ENEMY BACK TO DEFAULT
    }
}

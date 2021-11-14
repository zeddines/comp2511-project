package dungeonmania.entity.collectable;

import dungeonmania.entity.creature.Creature;
import dungeonmania.entity.creature.Player;
import dungeonmania.map.DungeonMapAPI;

public class InvisibilityEffect extends Effect{

    public InvisibilityEffect(Creature target, int lastFor, DungeonMapAPI game){
        super(target, lastFor, game);
    }

    @Override
    public void applyEffect() {
        getTarget().applyInvisibleBuff();
        //TODO SET ALL ENEMIES RANDOM MOVEMENT BEHAVIOUR

        //SHOULD CALL SOME FUNCTION IN MAPDUNGEON TO SET ALL ENEMY MOVEMENT TO RANDOM
    }

    @Override
    public void endEffect() {
        getTarget().revertInvisibleBuff();
        //TODO SET ALL ENEMIES BACK TO DEFAULT BEHAVIOUR

        //SHOULD CALL SOME FUNCTION IN MAPDUNGEON TO SET ALL ENEMY BACK TO DEFAULT
    }
}

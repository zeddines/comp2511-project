package dungeonmania.entity.creature;

import dungeonmania.DungeonManiaController;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.CollideActionEntity;
import dungeonmania.entity.interfaces.MovementNPC;
import dungeonmania.entity.interfaces.RegularActionEntity;
import dungeonmania.util.Position;

public class Enemy extends Creature implements CollideActionEntity, RegularActionEntity{

    private MovementNPC movement;
    
    public Enemy(DungeonManiaController game, String id, String type, Position position, boolean isInteractable,
                 MovementNPC movement, BattleStat battleStat) {
        super(game, id, type, position, isInteractable, battleStat);
        this.movement = movement;
    }

    @Override
    public void collideAction(Player player) {
        if (!player.isInvisible()){
            getGame().addToBattle(this);
        }
    }

    @Override
    public void regularAction() {
        movement.move();
    }
}

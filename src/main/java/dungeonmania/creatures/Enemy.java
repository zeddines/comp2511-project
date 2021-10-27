package dungeonmania.creatures;

import dungeonmania.BattleStat;
import dungeonmania.CollideActionEntity;
import dungeonmania.Creature;
import dungeonmania.DungeonManiaController;
import dungeonmania.MovementNPC;
import dungeonmania.RegularActionEntity;
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
        getGame().addToBattle(this);
    }

    @Override
    public void regularAction() {
        movement.move();
    }
}

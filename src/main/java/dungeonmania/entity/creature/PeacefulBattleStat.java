package dungeonmania.entity.creature;

import java.util.ArrayList;

import dungeonmania.entity.interfaces.BattleGear;

public class PeacefulBattleStat extends StandardBattleStat{

    public PeacefulBattleStat(Creature owner, double maxHealth, double attack, double defense,
            ArrayList<BattleGear> battleGears) {
        super(owner, maxHealth, attack, defense, battleGears);
    }

    public PeacefulBattleStat(Creature owner, double maxHealth, double attack, double defense) {
        super(owner, maxHealth, attack, defense);
    }
    
    @Override
    public double getAttack(){
        return 0;
    }
}

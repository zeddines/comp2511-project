package dungeonmania.entity.creature;

import java.util.ArrayList;

import dungeonmania.entity.interfaces.BattleGear;

public class BossBattleStat extends StandardBattleStat{

    public BossBattleStat(Creature owner, double maxHealth, double attack, double defense,
            ArrayList<BattleGear> battleGears) {
        super(owner, maxHealth, attack, defense, battleGears);
    }

    public BossBattleStat(Creature owner, double maxHealth, double attack, double defense) {
        super(owner, maxHealth, attack, defense);
    }

    @Override
    public void reduceHealth(double damageReceived, Creature enemy){
        if (enemy.getBattleStat().hasBossSlayer())
            super.reduceHealth(damageReceived*3, enemy);
        else
            super.reduceHealth(damageReceived, enemy);
    }
}

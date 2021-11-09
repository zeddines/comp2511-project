package dungeonmania.entity.creature;

import java.util.ArrayList;
import java.util.Random;

import dungeonmania.entity.interfaces.BattleGear;

public class HydraBattleStat extends BossBattleStat{

    public HydraBattleStat(Creature owner, double maxHealth, double attack, double defense,
            ArrayList<BattleGear> battleGears) {
        super(owner, maxHealth, attack, defense, battleGears);
    }

    public HydraBattleStat(Creature owner, double maxHealth, double attack, double defense) {
        super(owner, maxHealth, attack, defense);
    }

    @Override
    public void reduceHealth(double damageReceived, Creature enemy){
        if (enemy.getBattleStat().hasBossSlayer()){
            super.reduceHealth(damageReceived, enemy);
        }
        else{
            Random random = new Random();
            if (random.nextInt(2) == 1){
                recover(15);
            }
            else{
                super.reduceHealth(damageReceived, enemy);
            }
        }
    }
    
}

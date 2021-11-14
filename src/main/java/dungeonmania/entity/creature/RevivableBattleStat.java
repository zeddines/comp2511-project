package dungeonmania.entity.creature;

import java.util.ArrayList;

import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Ring;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.map.DungeonMap;

public class RevivableBattleStat extends StandardBattleStat{

    public RevivableBattleStat(Creature owner, double maxHealth, double attack, double defense,
            ArrayList<BattleGear> battleGears) {
        super(owner, maxHealth, attack, defense, battleGears);
    }

    public RevivableBattleStat(Creature owner, double maxHealth, double attack, double defense) {
        super(owner, maxHealth, attack, defense);
    }

    @Override
    public void reduceHealth(double damageReceived, Creature enemy){
        super.reduceHealth(damageReceived, enemy);
        if (getHealth() <= 0){
            ArrayList<Collectable> ownerCollectables = DungeonMap.shallowClone(getOwner().getNonBattleItems());
            for (Collectable collectable : ownerCollectables){
                if (collectable instanceof Ring){
                    recoverToFull();
                    collectable.removeFromInventory();
                }
            }
        }
    }
}

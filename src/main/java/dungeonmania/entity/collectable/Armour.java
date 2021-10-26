package dungeonmania.entity.collectable;

import dungeonmania.entity.interfaces.*;

public class Armour extends Collectable implements Guard, Consumable {
    private int durability; 
    private int remainingUses; 

    /**
     * body armour which provides defence and halves enemy attack
     * small proportion of zombies randomly spawn with armour which the char can 
     * take if they defeat a zombie in battle
     * some mercenaries have armour which the char can also take after battle
     * each piece of armour has a specific durability that dictates the num of times 
     * it can be used before it deteriorates 
     */
    public Armour() {
        super(); 
    }

    public void modifyDefence() {
        return; 
    }

    @Override
    public void collideAction(String player) { // Player player 
        return;
    }
}

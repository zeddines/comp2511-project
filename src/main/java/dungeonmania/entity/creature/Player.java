package dungeonmania.entity.creature;

import java.util.ArrayList;
import java.util.Iterator;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Key;
import dungeonmania.entity.collectable.Potion;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.BattleGear;
import dungeonmania.entity.interfaces.Usable;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Player extends Creature{

    private boolean isInvisible;
    private boolean isInvincible;

    public ArrayList<Potion> potionsInEffect;

    public Player(DungeonMapAPI map, String type, Position position) {
        super(map, type, position, new StandardBattleStat(100, 10, 0));
        potionsInEffect = new ArrayList<>();
        isInvisible = false;
        isInvincible = false; 
        map.setPlayer(this);
    }
    
    public void addPotionInEffect(Potion potion){
        potionsInEffect.add(potion);
    }

    private void resetBuffs(){
        isInvincible = false;
        isInvisible = false;
    }

    public void updatePotionEffects(){
        Iterator<Potion> potionIte = potionsInEffect.iterator(); 
        resetBuffs();
        while (potionIte.hasNext()){
            Potion potion = potionIte.next();
            potion.updateEffectDuration();
            if (potion.getDurationLeft() == 0){
                potionIte.remove();
            }   
        }
        
        for (Potion potionInEffect : potionsInEffect){
            potionInEffect.applyPotionEffect();
        }
        
    }

    //getter setters
    public boolean isInvisible() {
        return isInvisible;
    }

    public void setInvisible(boolean isInvisible) {
        this.isInvisible = isInvisible;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    public ArrayList<ItemResponse> inventoryToItemResponseList(){
        ArrayList<ItemResponse> returnList = new ArrayList<>();

        for (Collectable item : getNonBattleItems()){
            returnList.add(new ItemResponse(item.getId(), item.getType()));
        }

        for (BattleGear battleGear : getBattleGears()){
            Entity battleGearAsEntity = (Entity)battleGear;
            returnList.add(new ItemResponse(battleGearAsEntity.getId(), battleGearAsEntity.getType()));
        }

        return returnList;
    }

    public void use(String id) throws IllegalArgumentException, InvalidActionException{
        Entity entity = getNonBattleItemFromInventory(id);
        if (entity == null){
            throw new InvalidActionException(id);
        }
        if (!(entity instanceof Usable))
            throw new IllegalArgumentException("item is not usable");
        Usable item = (Usable)entity;    
        item.use();
    }

}

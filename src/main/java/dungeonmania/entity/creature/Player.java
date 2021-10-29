package dungeonmania.entity.creature;

import java.util.ArrayList;

import dungeonmania.entity.Entity;
import dungeonmania.entity.EntityAPI;
import dungeonmania.entity.collectable.Collectable;
import dungeonmania.entity.collectable.Potion;
import dungeonmania.entity.interfaces.BattleStat;
import dungeonmania.entity.interfaces.Guard;
import dungeonmania.entity.interfaces.Usable;
import dungeonmania.entity.interfaces.Weapon;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Player extends Creature{

    private boolean isInvisible;
    private boolean isInvincible;

    public ArrayList<Potion> potionsInEffect;

    public Player(DungeonMapAPI game, String type, Position position, boolean isInteractable,
            BattleStat battleStat) {
        super(game, type, position, isInteractable, new StandardBattleStat(100, 10, 0));
        potionsInEffect = new ArrayList<>();
        isInvisible = false;
        isInvincible = false; 
    }

    //methods about potion
    public void addPotionInEffect(Potion potion){
        potionsInEffect.add(potion);
    }

    public void removePotionEffect(Potion potion){
        potionsInEffect.remove(potion);
    }

    public void updatePotionEffects(){
        for (Potion potion : potionsInEffect){
            //will remove potions in potionsInEffect array if the effect has ended
            potion.updateEffectDuration();
        }
        for (Potion potion : potionsInEffect){
            potion.applyPotionEffect();
        }
    }
    //

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

    public ArrayList<ItemResponse> inventoryToItemResposne(){
        ArrayList<ItemResponse> returnList = new ArrayList<>();

        for (Collectable item : getNonBattleItems()){
            returnList.add(new ItemResponse(item.getId(), item.getType()));
        }

        for (Weapon weapon : getOwnedWeapons()){
            Entity weaponAsEntity = (Entity)weapon;
            returnList.add(new ItemResponse(weaponAsEntity.getId(), weaponAsEntity.getType()));
        }

        for (Guard guard : getOwnedGuards()){
            Entity guardAsEntity = (Entity)guard;
            returnList.add(new ItemResponse(guardAsEntity.getId(), guardAsEntity.getType()));
        }

        return returnList;
    }

    public void use(String id) throws IllegalArgumentException, InvalidActionException{
        //Entity entity = getGame().getEntityFromId(id);
        Entity entity = getNonBattleItemFromInventory(id);
        if (entity == null){
            throw new InvalidActionException(id);
        }
        if (!(entity instanceof Usable))
            throw new IllegalArgumentException();
        Usable item = (Usable)entity;       
        item.use();
    }

    @Override
    public void action(Player player) {
        return;
    }


}

package dungeonmania.creatures;

import java.util.ArrayList;

import dungeonmania.Creature;
import dungeonmania.DungeonManiaController;
import dungeonmania.Entity;
import dungeonmania.Guard;
import dungeonmania.Usable;
import dungeonmania.Weapon;
import dungeonmania.collectables.Potion;
import dungeonmania.BattleStat;
import dungeonmania.Collectable;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Player extends Creature{
    //buffs
    //TODO
    private boolean isInvisible;
    private boolean isInvincible;

    public ArrayList<Potion> potionsInEffect;

    public void addPotionInEffect(Potion potion){
        potionsInEffect.add(potion);
    }

    public void removePotionEffect(Potion potion){
        potionsInEffect.remove(potion);
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

    //TODO MOVEMENT IN CREATURES AND ENEMIES 

    //movementOptions and stuff



    //player movement
    

    public Player(DungeonManiaController game, String id, String type, Position position, boolean isInteractable,
            BattleStat battleStat) {
        super(game, id, type, position, isInteractable, battleStat);
        potionsInEffect = new ArrayList<>();
        isInvisible = false;
        isInvincible = false;
    }

    //TODO NOT MENTIONED IN UML (changed method name)
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
        Entity entity = getGame().getEntityFromId(id);
        
        if (!(entity instanceof Usable))
            throw new IllegalArgumentException();

        Usable item = (Usable)entity;
        if (!getNonBattleItems().contains(item))
            throw new InvalidActionException(id);
        
        item.use();
    }
}

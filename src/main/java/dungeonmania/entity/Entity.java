package dungeonmania.entity;

import dungeonmania.entity.creature.Enemy;
import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.Interactable;
import dungeonmania.entity.interfaces.MovableNPC;
import dungeonmania.entity.square.Boulder;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public abstract class Entity implements EntityAPI{
    public static Integer numEntities = 0;

    protected DungeonMapAPI game;

    private String id;
    private String type;
    private Position position;
    
    public Entity(DungeonMapAPI dungeon, Position current, String type) {
        numEntities++;
        this.position = current;
        this.type = type;
        this.id = numEntities.toString();
        this.game = dungeon;
    }

    @Override
    public boolean isHostile(){
        return (this instanceof Enemy) && !(getGame().getAllies().contains(this));
    }

    @Override
    public boolean isInteractable(){
        return this instanceof Interactable;
    }

    @Override
    public EntityResponse toEntityResponse(){
        return new EntityResponse(id, type, position, isInteractable());
    }
    
    @Override
    public void collideAction(Player player) {
        return;
    }

    @Override
    public void collideAction(Boulder boulder){
        return;
    }

    @Override
    public void leaveAction(Player player){
        return;
    }
    
    @Override
    public void leaveAction(Boulder boulder){
        return;
    }

    
    @Override
    public boolean canBeOnSamePosition(Player player) {
        return true;
    }

    @Override
    public boolean canBeOnSamePosition(Enemy enemy) {
        return true;
    }

    @Override
    public boolean canBeOnSamePosition(Boulder boulder){
        return true;
    }
    
    //setter getters

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public DungeonMapAPI getGame() {
        return game;
    }

    public void setGame(DungeonMapAPI game) {
        this.game = game;
    }
}

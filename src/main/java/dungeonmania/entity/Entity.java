package dungeonmania.entity;

import dungeonmania.entity.creature.Player;
import dungeonmania.entity.interfaces.RegularActionEntity;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public abstract class Entity implements EntityAPI{
    public static Integer numEntities = 0;

    protected DungeonMapAPI game;

    private String id;
    private String type;
    private Position position;
    private boolean isInteractable;
    
    public Entity(DungeonMapAPI dungeon, Position current, String type, boolean interactable) {
        numEntities++;
        this.position = current;
        this.type = type;
        this.id = numEntities.toString();
        this.isInteractable = interactable;
        this.game = dungeon;
    }

    public boolean isDynamic(){
        return (this instanceof RegularActionEntity);
    }

    @Override
    public EntityResponse getInfo(){
        return new EntityResponse(id, type, position, isInteractable);
    }

    public boolean canCoExist() {
        return true;
    }
    
    //setter getters
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

    public boolean isInteractable() {
        return isInteractable;
    }

    public void setInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    public DungeonMapAPI getGame() {
        return game;
    }

    public void setGame(DungeonMapAPI game) {
        this.game = game;
    }

    public void action(Player player) {
        return;
    }
}

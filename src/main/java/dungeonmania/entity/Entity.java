package dungeonmania.entity;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class Entity {
    //TODO NOT MENTIONED IN UML
    private DungeonManiaController game;

    private String id;
    private String type;
    private Position position;
    private boolean isInteractable;
    
    public Entity(DungeonManiaController game, String id, String type, Position position, boolean isInteractable) {
        this.game = game;
        this.id = id;
        this.type = type;
        this.position = position;
        this.isInteractable = isInteractable;
    }

    public EntityResponse toEntityResponse(){
        return new EntityResponse(id, type, position, isInteractable);
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

    public DungeonManiaController getGame() {
        return game;
    }

    public void setGame(DungeonManiaController game) {
        this.game = game;
    }

    
}

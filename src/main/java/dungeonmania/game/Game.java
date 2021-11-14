package dungeonmania.game;

import dungeonmania.entity.creature.Player;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.map.*;
import dungeonmania.response.models.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import java.util.ArrayList;

public class Game implements GameAPI {

    private String dungeonName;
    private String dungeonId = "";
    public static Integer numGames = 0;
    private String gameMode;
    private DungeonMapAPI map;
    private MapBuilderAPI factory = new MapBuilder();

    public Game(String dungeonName, String gameMode) {
        this.dungeonName = dungeonName;
        this.gameMode = gameMode;
        this.map =  factory.build(dungeonName, gameMode);
        this.dungeonId = numGames.toString();
        numGames++;
    }

    public String getId() {
        return dungeonId;
    }

    @Override
    public DungeonResponse toDungeonResponse() {
        map.getGoals();
        DungeonResponse dungeonResponse = new DungeonResponse(dungeonId, dungeonName, map.toEntityResponseList(), map.toItemResponseList() , map.buildableItems(), map.getGoals());
        return dungeonResponse;
    }

    public DungeonResponse tick(String itemUsed, Direction movementDirection) throws IllegalArgumentException, InvalidActionException {
        map.tick(itemUsed, movementDirection);
        return toDungeonResponse();
    }

    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException{
        map.interact(entityId);
        return toDungeonResponse();
    }

    public void setMap(DungeonMap map) {
        this.map = map;
    }

    public void setID(String name) {
        this.dungeonId = name;
    }

    public Player getPlayer() {
        return map.getPlayer();
    }

    @Override
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException{
        map.build(buildable);
        return toDungeonResponse();
    }
}

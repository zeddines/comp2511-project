package dungeonmania.game;

import dungeonmania.entity.creature.Player;
import dungeonmania.map.*;
import dungeonmania.response.models.*;
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

    public DungeonResponse getInfo() {
        map.getGoals();
        DungeonResponse hello = new DungeonResponse(dungeonId, dungeonName, map.getInfoList(), map.getItemInfoList() , new ArrayList<String>(), map.getGoals());
        return hello;
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

    public boolean checkLocation(Position check) {
        return map.checkLocation(check);
    }

    public void collideAction(Player player, Position currentPosition) {
        map.collideAction(player, currentPosition);
    }
}

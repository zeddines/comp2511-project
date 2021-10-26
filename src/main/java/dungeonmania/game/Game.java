package dungeonmania.game;

import dungeonmania.map.DungeonMap;
import dungeonmania.response.models.*;

public class Game implements GameAPI {

    private String dungeonName;
    private String dungeonId;
    public static Integer numGames;
    private String gameMode;
    private DungeonMap map;

    public Game(String dungeonName, String gameMode) {
        this.dungeonName = dungeonName;
        this.gameMode = gameMode;
        this.dungeonId = numGames.toString();
        numGames++;
    }

    public String getId() {
        return dungeonId;
    }

    public DungeonResponse getInfo() {
        return null;
    }

    public void setMap(DungeonMap map) {
        this.map = map;
    }
}

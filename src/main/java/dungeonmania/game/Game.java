package dungeonmania.game;

import dungeonmania.response.models.*;

public class Game implements GameAPI {

    private String dungeonName;
    private String dungeonId;
    //use for dungeonId
    public static Integer numGames;
    public String gameMode;

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
}

package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Hashtable; 
import java.util.List;
import dungeonmania.game.*;
import static dungeonmania.util.FileLoader.listFileNamesInResourceDirectory;

import org.json.JSONArray;
import org.json.JSONObject;

public class DungeonManiaController {
    
    //key is the dungeonId -> use static variable for number of dungeons -> what happens if we finish a game?
    private Map<String,GameAPI> games;
    public DungeonManiaController() {

        this.games = new Hashtable<>();
    }

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    public List<String> getGameModes() {
        return Arrays.asList("Standard", "Peaceful", "Hard");
    }

    /**
     * /dungeons
     * 
     * Done for you.
     */
    public static List<String> dungeons() {
        try {
            return FileLoader.listFileNamesInResourceDirectory("/dungeons");
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public DungeonResponse newGame(String dungeonName, String gameMode) throws IllegalArgumentException {
        try {    
            if (!(getGameModes().contains(gameMode))) {
                throw new IllegalArgumentException();
            } else if (!FileLoader.listFileNamesInResourceDirectory("dungeons").contains(dungeonName)) {
                throw new IllegalArgumentException();
            } else {
                GameAPI newGame = new Game(dungeonName, gameMode);
                games.put(newGame.getId(), newGame);
                return newGame.getInfo();
            }
        }
        catch(IOException e) {
            return null;
        } 
    }
    
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        return null;
    }

    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        return null;
    }

    public List<String> allGames() {
        return new ArrayList<>();
    }

    public DungeonResponse tick(String itemUsed, Direction movementDirection) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return null;
    }
}
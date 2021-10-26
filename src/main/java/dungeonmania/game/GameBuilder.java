package dungeonmania.game;

public class GameBuilder implements GameBuilderAPI {

    public Game build(String dungeonName, String gameMode) {
        return new Game(dungeonName, gameMode);
    }
    
}

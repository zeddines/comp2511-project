package dungeonmania.game;

import dungeonmania.map.DungeonMapAPI;

public class GameBuilder implements GameBuilderAPI {

    public Game build(String dungeonName, String gameMode) {
        return new Game(dungeonName, gameMode);
    }
    
}

package dungeonmania.game;

import dungeonmania.map.DungeonMapAPI;

public interface GameBuilderAPI {

    public Game build(String dungeonName, String gameMode);
    
}

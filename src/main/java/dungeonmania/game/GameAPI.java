package dungeonmania.game;

import dungeonmania.response.models.*;
public interface GameAPI {

    public String getId();   
    
    public DungeonResponse getInfo();

    public void setID(String name);


    
}

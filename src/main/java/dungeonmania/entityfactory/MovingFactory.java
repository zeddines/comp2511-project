package dungeonmania.entityfactory;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import dungeonmania.entity.creature.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.entity.*;
import dungeonmania.util.*;

public class MovingFactory extends PrimaryFactory {

    public static final String[] movingEntites = {"spider", "zombie_toast", "mercenary", "player"};

    public MovingFactory() {
        super(movingEntites);

    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        String type = entityContents.getString("type");
        if (type.equals("spider")){
            NonInteractableEnemy spider =  new NonInteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            spider.setBattleStat(new StandardBattleStat(spider, 5, 5, 0));
            spider.setMovement(null);
            return spider;
        }
        else if (type.equals("zombie_toast")){
            NonInteractableEnemy zombieToast =  new NonInteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            zombieToast.setBattleStat(new StandardBattleStat(zombieToast, 5, 5, 0));
            zombieToast.setMovement(null);
            return zombieToast;
        }
        else if (type.equals("mercenary")){
            InteractableEnemy mercenary =  new InteractableEnemy(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
            mercenary.setBattleStat(new StandardBattleStat(mercenary, 10, 0.1, 0));
            mercenary.setItemsRequiredToBribe(Arrays.asList("treasure", "treasure"));
            mercenary.setMovement(null);
            return mercenary;
        }
        else if (type.equals("player")){
            Player player = new Player(map, type, new Position(entityContents.getInt("x"), entityContents.getInt("y")));    
            player.setBattleStat(new RevivableBattleStat(player, 10, 0.1, 0));
            return player;
        }
        return null;
    }
    
}

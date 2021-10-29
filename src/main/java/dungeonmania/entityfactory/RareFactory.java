package dungeonmania.entityfactory;
import dungeonmania.entity.collectable.*;
import dungeonmania.map.DungeonMapAPI;
import dungeonmania.util.Position;
import dungeonmania.entity.*;
import org.json.JSONObject;
import java.util.Arrays;

public class RareFactory extends PrimaryFactory{

    public static final String[] rare = {"one_ring"};

    public RareFactory() {
        super(rare);
    }

    @Override
    public Entity build(JSONObject entityContents, DungeonMapAPI map) {
        return new Ring(new Position(entityContents.getInt("x"), entityContents.getInt("y")),entityContents.getString("type"), map);
    }

    
}

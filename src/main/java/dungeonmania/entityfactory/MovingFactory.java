package dungeonmania.entityfactory;
import org.json.JSONObject;
import dungeonmania.entity.creature.*;
import dungeonmania.entity.*;

public class MovingFactory extends PrimaryFactory {

    public static final String[] movingEntites = {"spider", "zombie_toast", "mercenary"};

    public MovingFactory() {
        super(movingEntites);

    }

    @Override
    public Entity build(JSONObject entityContents) {
        String type = entityContents.getString("type");
        if (type.equals("spider")) {


        }
            return new Spider(entityContents);
        else if (type.equals("zombie_toast")) {

            return new ZombieToast(entityContents);
        }  
        else 
            return new Mercenary(entityContents);
    }
    
}

package dungeonmania.entityfactory;
import dungeonmania.entity.*;
import org.json.JSONObject;

public interface FactoryAPI {

    public Entity build(JSONObject entityContents);
    
}

package dungeonmania.entityfactory;
import java.util.Arrays;

public class RareFactory extends PrimaryFactory{

    public static final String[] rare = {"one_ring"};

    public RareFactory() {
        super(rare);

    }

    public boolean check(String type) {
        return Arrays.asList(rare).contains(type);
    }
    
}

package dungeonmania.map;
import dungeonmania.cells.*;

public class MapBuilder implements MapBuilderAPI {

    public static int MAX_HEIGHT = 1000;
    public static int MAX_WIDTH = 1000;

    
    public Square[][] buildMap(String dungeonName) {

        Square[][] newMap = emptyMap();

        return null;

    }
    
    public Square[][] emptyMap() {

        Square[][] emptyMap = new Square[MAX_HEIGHT][MAX_WIDTH];
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                emptyMap[i][j] = new Square();
            }
        }  
        return emptyMap;

    }
    
}

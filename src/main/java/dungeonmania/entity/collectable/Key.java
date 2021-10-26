package dungeonmania.entity.collectable;

public class Key extends Collectable {
    private String door; 

    /**
     * can be picked up by the player when they move into the square containing it
     * character can only carry one key at a time, and only one door has a lock that 
     * fits that key
     * the key also disappears once it is used to open its corresponding door  
     */
    public Key() {
        super(); 
    }

    public void use() {
        return; 
    }

}

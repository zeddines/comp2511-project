package dungeonmania.entity.collectable;

public class Collectable {
    // private Player owner; 
    private boolean inInventory; 

    public Collectable() {
        this.inInventory = false; 
    }

    /**
     * remove the collectable from undeon
     * set position to null and add to player inventory 
     * @param player    Player player 
     */
    public void collideAction(String player) { // Player player 
        return;
    }

    public void removeFromInventory() {
        return; 
    }
}

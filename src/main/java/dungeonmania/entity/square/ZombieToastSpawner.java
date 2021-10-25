package dungeonmania.entity.square;

public class ZombieToastSpawner extends Square {
    public ZombieToastSpawner(int x, int y) {
        super(x,y); 
    }

    /**
     *  Spawns zombie toasts every 20 ticks in an open square cardinally adjacent to the spawner 
     *  Character can destroy a zombie spawner if they have a weapon and are cardinally adjacent 
     *  to spawner 
     */
    @Override
    public void action(String s) {
        return; 
    }
}

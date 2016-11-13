package dining_philosophers;

/**
 * Created by faielomente on 11/12/16.
 */
public class Philosopher {
    private int id;
    private boolean hasEaten = false;

    Philosopher(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isHasEaten() {
        return hasEaten;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHasEaten(boolean hasEaten) {
        this.hasEaten = hasEaten;
    }

}

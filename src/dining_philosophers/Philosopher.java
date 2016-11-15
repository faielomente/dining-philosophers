package dining_philosophers;

/**
 * Created by faielomente on 11/12/16.
 */
public class Philosopher {
    private int id;
    private boolean isEating = false;
    private boolean done = false;

    Philosopher(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isIsEating() {
        return isEating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsEating(boolean isEating) {
        this.isEating = isEating;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}

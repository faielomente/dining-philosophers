package dining_philosophers;

/**
 * Created by faielomente on 11/12/16.
 */
public class Philosophers {
    String name = "Philosopher";
    int seat_no;
    int id;
    boolean leftChopstick = false;
    boolean rightChopstick = false;

    Philosophers(String name, int seat_no, int id){
        this.id = id;
        this.name = this.name + String.valueOf(id);
        this.seat_no = seat_no;
    }

    public void cquireLeft(){
        this.leftChopstick = true;
    }

    public void aquireRight(){
        this.rightChopstick = true;
    }
}

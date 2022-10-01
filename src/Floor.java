import javax.naming.directory.DirContext;
import java.util.ArrayList;

public class Floor {
    boolean lightDown = false;
    boolean lightUp = false;
    int assignedElevator = -1;

    ArrayList<Passenger> passengers;

    public Floor() {
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Direction direction,Passenger passenger){

        if(direction == Direction.UP){
            lightUp = true;
        }else if(direction == Direction.DOWN) {
            lightDown = true;
        }
        this.passengers.add(passenger);

    }

    public void clear(){
        this.lightUp =false;
        this.lightDown =false;
        this.passengers.clear();
        this.assignedElevator = -1;
    }

    public String toString(){
        String s="";
        for(Passenger passenger : passengers){
            s+=passenger+" ";
        }
        return s;
    }
}

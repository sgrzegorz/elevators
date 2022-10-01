import java.util.ArrayList;

public class Elevator {
    int id;
    int currentFloor;
    int targetFloor;

    ArrayList<Passenger> passengers;


    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public Elevator(int id, int currentFloor, int targetFloor){
        this.currentFloor = currentFloor;
        this.id = id;
        this.targetFloor = targetFloor;
        this.passengers = new ArrayList<Passenger>();

    }

    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
    }

    public void removePassenger(int stoppingFloorId){
        for(Passenger passenger : passengers){
            if(passenger.destinationFloor == stoppingFloorId){
                passengers.remove(passenger);
            }
        }
    }
}

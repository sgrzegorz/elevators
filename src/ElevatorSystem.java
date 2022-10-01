import java.util.ArrayList;

public class ElevatorSystem {

    public ElevatorSystem(int elevatorNumber, int highestFloor){
        this.elevators = new ArrayList<Elevator>();
        for(int i=0;i< elevatorNumber;i++){
            Elevator elevator = new Elevator(i, 0, 0);
            this.elevators.add(elevator);
        }
        this.elevatorNumber = elevatorNumber;
        this.highestFloor = highestFloor;

    }
    public ArrayList<Elevator> elevators;
    int elevatorNumber;
    int highestFloor;

    public ArrayList<Pickup> pickups;


    void pickup(int passengerFloor, Enum direction){
        pickups.add(new Pickup(passengerFloor,direction));
    }

    void update(int elevatorId, int currentFloor, int targetFloor){
        this.elevators.get(elevatorId).setTargetFloor(targetFloor);
        this.elevators.get(elevatorId).setCurrentFloor(currentFloor);
    }

    void step(){
        for(Elevator elevator : elevators){
            if(elevator.currentFloor ==elevator.targetFloor){

            }
        }
    }

    void status(){
        System.out.println("------------------------------------------------------");

        for(Elevator elevator : this.elevators){
            System.out.println("elevator:"+elevator.id +"\tposition:"+elevator.currentFloor+"\tdestination:"+elevator.targetFloor);
        }
    }
}

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Generator {
    ElevatorSystem elevatorSystem;
    ArrayList<Passenger> passengers;


    public Generator(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
        this.passengers = new ArrayList<Passenger>();
    }

    public void newPassenger(int entryStep, int entryFloor, int destinationFloor){
        if(destinationFloor >= elevatorSystem.numberOfFloors) {
            throw new RuntimeException("Floor with id " + destinationFloor+ " does not exist");
        }
        Passenger passenger = new Passenger(destinationFloor,entryFloor,entryStep);
        passengers.add(passenger);
    }

    private void removePassenger(Passenger passenger){
        this.passengers.remove(passenger);

    }

    public void simulate(int maxStep) throws InterruptedException {
        ElevatorSystem.log(passengersString());

        int step=0;
        while(step <=maxStep){

            // Add new Passengers for current step
            List<Passenger> found= new ArrayList<>();
            for(Passenger passenger : passengers){
                if(passenger.entryStep == step){

                    Direction direction = Direction.UP;
                    if(passenger.entryFloor > passenger.destinationFloor){
                        direction = Direction.DOWN;
                    }else if(passenger.entryFloor == passenger.destinationFloor){
                        new RuntimeException("Passenger destination is his entryFloor");
                    }

                    this.elevatorSystem.pickup(passenger.entryFloor,direction,passenger);
                    found.add(passenger);

                }
            }
            this.passengers.removeAll(found);


            if(step==0){
                this.elevatorSystem.status(-1);
            }

            this.elevatorSystem.step(step);

            this.elevatorSystem.status(step);
            sleep(1000);

            step+=1;
            if(finished()){
                break;
            }
        }
        ElevatorSystem.log(this.elevatorSystem.toString());
    }

    String passengersString(){
        String s="";
        for(Passenger passenger : passengers){
            s+=passenger+ "\tentry:floor"+passenger.entryFloor+" --> \tdestination:floor"+passenger.destinationFloor+"\n";
        }
        return s;
    }

    boolean finished(){
        if(this.passengers.size()!=0){
            return false;
        }

        for(Elevator elevator : this.elevatorSystem.elevators){
            if(elevator.passengers.size()!=0){
                return false;
            }
        }
        for(Floor floor : elevatorSystem.floors){
            if(floor.passengers.size()!=0){
                return false;
            }
        }

        return true;

    }


}

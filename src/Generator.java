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

                }
            }
            this.passengers.removeAll(found);

            this.elevatorSystem.status(step);
            this.elevatorSystem.step(step);

            sleep(1000);

            step+=1;
        }
        ElevatorSystem.log(this.elevatorSystem.toString());
    }

    String passengersString(){
        String s="";
        for(Passenger passenger : passengers){
            s+="passengerId"+passenger.id+ "\tentry:floor:"+passenger.entryFloor+" --> \tdestination:floor"+passenger.destinationFloor+"\n";
        }
        return s;
    }


}

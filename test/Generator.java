import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class Generator {
    ElevatorSystem elevatorSystem;
    ArrayList<Passenger> passengers;
    HashMap<Passenger,Integer> entrySteps;
    HashMap<Passenger,Integer> entryFloor;


    public Generator(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
        this.passengers = new ArrayList<Passenger>();
        this.entrySteps = new HashMap<Passenger,Integer>();
    }

    public void newPassenger(int step, int entryFloor, int destinationFloor){
        Passenger passenger = new Passenger(destinationFloor,entryFloor);
        passengers.add(passenger);
        this.entrySteps.put(passenger,step);
        this.entryFloor.put(passenger,entryFloor);
    }

    private void removePassenger(Passenger passenger){

        this.entrySteps.remove(passenger);
        this.entryFloor.remove(passenger);
        this.passengers.remove(passenger);

    }

    public void simulate() throws InterruptedException {
        int step=0;
        while(true){
            for(Passenger passenger : passengers){
                if(entrySteps.get(passenger)== step){
                    Enum Direction;
                    if(passenger.entryFloor >= passenger.destinationFloor)

                    this.elevatorSystem.pickup();

                    removePassenger(passenger);
                }
            }
            this.elevatorSystem.step();
            this.elevatorSystem.status();
            sleep(1000);

            step+=1;
        }
    }
}

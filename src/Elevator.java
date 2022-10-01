import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Elevator {
    int id;
    int currentFloor;
    int targetFloor;
    Action action;
    ArrayList<Passenger> passengers;
    ElevatorSystem elevatorSystem;

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public Elevator(int id, int currentFloor, int targetFloor,ElevatorSystem elevatorSystem){
        this.currentFloor = currentFloor;
        this.id = id;
        this.targetFloor = targetFloor;
        this.passengers = new ArrayList<Passenger>();
        this.elevatorSystem = elevatorSystem;
        this.action = Action.STOP;

    }

    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
    }

    public void removePassenger(int stoppingFloorId){
        List<Passenger> found = new ArrayList<>();
        for(Passenger passenger : passengers){
            if(passenger.destinationFloor == stoppingFloorId){
                found.add(passenger);
            }
        }
        this.passengers.removeAll(found);
    }

    private int countUpRequests(){
        int count = 0;
        for(int i=0;i<this.elevatorSystem.floors.size();i++){
            Floor floor = this.elevatorSystem.floors.get(i);
            if(floor.assignedElevator == this.id){
                if(this.currentFloor < i){
                    count++;
                }
            }
        }

        for(Passenger passenger : this.passengers){
            if(passenger.destinationFloor>currentFloor){
                count++;
            }
        }
        return count;
    }

    private int countDownRequests(){
        int count = 0;
        for(int i=0;i<this.elevatorSystem.floors.size();i++){
            Floor floor = this.elevatorSystem.floors.get(i);
            if(floor.assignedElevator == this.id){
                if(this.currentFloor > i){
                    count++;
                }
            }
        }

        for(Passenger passenger : this.passengers){
            if(passenger.destinationFloor<currentFloor){
                count++;
            }
        }
        return count;
    }

    private Action nextAction(){
        if(this.action == Action.UP){
            for(Floor floor : elevatorSystem.floors){
                if(floor.assignedElevator == this.id){
                    if(floor.lightUp){
                        return Action.UP;
                    }
                }
            }

            for(Passenger passenger : this.passengers){
                if(passenger.destinationFloor>currentFloor){
                    return Action.UP;
                }
            }

            for()

        }else if(this.action == Action.DOWN){
            for(Floor floor : elevatorSystem.floors){
                if(floor.assignedElevator == this.id){
                    if(floor.lightDown){
                        return Action.DOWN;
                    }
                }
            }
            for(Passenger passenger : this.passengers){
                if(passenger.destinationFloor<currentFloor){
                    return Action.DOWN;
                }
            }
        }else{ //this.action == Action.STOP
            int actionsUp = countUpRequests();
            int actionsDown = countDownRequests();
            if(actionsUp>actionsDown){
                return Action.UP;
            }else if(actionsUp<actionsDown){
                return Action.DOWN;
            }else if(actionsUp==actionsDown && actionsUp!=0){
                return Action.UP;
            }
        }
        return Action.STOP;
    }



    public void step(int step){

        // Find next action
        Action nextAction = nextAction();
        this.action =nextAction;

        if(this.action == Action.DOWN && this.currentFloor!=0){
            this.currentFloor-=1;
        } else if (this.action==Action.UP && this.currentFloor!=elevatorSystem.numberOfFloors-1) {
            this.currentFloor+=1;
        }

        // Open doors, let passengers leave the elevator
        List<Passenger> found = new ArrayList<>();
        for(Passenger passenger : this.passengers){
            if(this.currentFloor == passenger.destinationFloor){
                found.add(passenger);
                ElevatorSystem.log("Elevator" + this.id + "\tfloor" + this.currentFloor+ "\tleft " +  passenger);
            }
        }
        this.passengers.removeAll(found);
        //take passengers into the elevator
        Floor floor1 = elevatorSystem.floors.get(this.currentFloor);
        if(floor1.assignedElevator == this.id){
            ElevatorSystem.log("Elevator" + this.id + "\tfloor" + this.currentFloor+", took "+ floor1);
            this.passengers.addAll(floor1.passengers);
            floor1.clear();
        }


    }

    public String toString(){

        String s="ElevatorId"+this.id+" ";
        s+="passengers: ";
        for(Passenger passenger : this.passengers){
            s+="Passenger"+passenger.id+" ";
        }
        s+="\t currentFloor:floor" + this.currentFloor;
        s+="\n";
        return s;
    }
}

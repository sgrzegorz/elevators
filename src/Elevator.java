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
    ArrayList<Boolean> destinationFloors;

    public Elevator(int id, int currentFloor, int targetFloor, ElevatorSystem elevatorSystem) {
        this.currentFloor = currentFloor;
        this.id = id;
        this.targetFloor = targetFloor;
        this.passengers = new ArrayList<Passenger>();
        this.elevatorSystem = elevatorSystem;
        this.action = Action.STOP;
        this.destinationFloors = new ArrayList<>();
        for (int i = 0; i < this.elevatorSystem.numberOfFloors; i++) {
            this.destinationFloors.add(false);
        }
        System.out.println();
    }


    public int getWorkload() {
        int workload = 0;
        for (int i = 0; i < elevatorSystem.numberOfFloors; i++) {
            if (this.destinationFloors.get(i)) {
                workload++;
            }
        }

        for (int i = 0; i < this.elevatorSystem.floors.size(); i++) {
            Floor floor = this.elevatorSystem.floors.get(i);
            if (floor.assignedElevator == this.id) {
                workload++;
            }
        }

        return workload;
    }

    private Action nextAction() {
        if (this.action == Action.UP) {
            if (countUpRequests() > 0) {
                return Action.UP;
            } else if (countDownRequests() > 0) {
                return Action.DOWN;
            }

        } else if (this.action == Action.DOWN) {
            if (countDownRequests() > 0) {
                return Action.DOWN;
            } else if (countUpRequests() > 0) {
                return Action.UP;
            }
        } else { //this.action == Action.STOP
            int actionsUp = countUpRequests();
            int actionsDown = countDownRequests();
            if (actionsUp > actionsDown) {
                return Action.UP;
            } else if (actionsUp < actionsDown) {
                return Action.DOWN;
            } else if (actionsUp == actionsDown && actionsUp != 0) {
                return Action.UP;
            }
        }
        return Action.STOP;
    }


    public void step(int step) {

        // Find next action for elevator
        Action nextAction = nextAction();
        this.action = nextAction;
        // Do next action
        if (this.action == Action.DOWN && this.currentFloor != 0) {
            this.currentFloor -= 1;
        } else if (this.action == Action.UP && this.currentFloor != elevatorSystem.numberOfFloors - 1) {
            this.currentFloor += 1;
        }

        // Open doors, let passengers leave the elevator
        List<Passenger> found = new ArrayList<>();
        for (Passenger passenger : this.passengers) {
            if (this.currentFloor == passenger.destinationFloor) {
                found.add(passenger);
                ElevatorSystem.log("Elevator" + this.id + "\tfloor" + this.currentFloor + "\tleft " + passenger);
            }
        }
        this.destinationFloors.set(this.currentFloor, false);
        this.passengers.removeAll(found);

        //take new passengers into the elevator
        Floor floor1 = elevatorSystem.floors.get(this.currentFloor);
        if (floor1.assignedElevator == this.id) {
            ElevatorSystem.log("Elevator" + this.id + "\tfloor" + this.currentFloor + ", took " + floor1);
            for (Passenger passenger : floor1.passengers) {
                this.destinationFloors.set(passenger.destinationFloor, true);
            }
            this.passengers.addAll(floor1.passengers);
            floor1.clear();
        }


    }

    public String toString() {

        String s = "ElevatorId" + this.id + " ";
        s += "passengers: ";
        for (Passenger passenger : this.passengers) {
            s += "Passenger" + passenger.id + " ";
        }
        s += "\t currentFloor:floor" + this.currentFloor;
        s += "\n";
        return s;
    }

    private int countUpRequests() {
        int count = 0;
        for (int i = 0; i < this.elevatorSystem.floors.size(); i++) {
            Floor floor = this.elevatorSystem.floors.get(i);
            if (floor.assignedElevator == this.id) {
                if (this.currentFloor < i) {
                    count++;
                }
            }
        }

        for (int i = 0; i < this.elevatorSystem.numberOfFloors; i++) {
            if (i > currentFloor && this.destinationFloors.get(i) == true) {
                count++;
            }
        }
        return count;
    }

    private int countDownRequests() {
        int count = 0;
        for (int i = 0; i < this.elevatorSystem.floors.size(); i++) {
            Floor floor = this.elevatorSystem.floors.get(i);
            if (floor.assignedElevator == this.id) {
                if (this.currentFloor > i) {
                    count++;
                }
            }
        }

        for (int i = 0; i < this.elevatorSystem.numberOfFloors; i++) {
            if (i < currentFloor && this.destinationFloors.get(i) == true) {
                count++;
            }
        }
        return count;
    }
}

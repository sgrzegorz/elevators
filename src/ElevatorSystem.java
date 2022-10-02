import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class ElevatorSystem {
    ArrayList<Floor> floors;

    public ElevatorSystem(int elevatorNumber, int numberOfFloors) {
        this.elevators = new ArrayList<Elevator>();
        this.elevatorNumber = elevatorNumber;
        this.numberOfFloors = numberOfFloors;
        this.floors = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i += 1) {
            this.floors.add(new Floor());
        }

        for (int i = 0; i < elevatorNumber; i++) {
            Elevator elevator = new Elevator(i, 0, 0, this);
            this.elevators.add(elevator);
        }

    }

    public ArrayList<Elevator> elevators;
    int elevatorNumber;
    int numberOfFloors;


    void pickup(int passengerFloor, Direction direction, Passenger passenger) {
        Floor floor = floors.get(passengerFloor);
        floor.addPassenger(direction, passenger);
    }

    void step(int step) {
        for (Floor floor : floors) {
            if (floor.passengers.size() != 0 && floor.assignedElevator == -1) {
//                For testing
//                Random randomizer = new Random();
//                Elevator elevator = elevators.get(randomizer.nextInt(elevators.size()));
//                floor.assignedElevator = elevator.id;

                floor.assignedElevator = assignElevator(floor);

            }
        }

        for (Elevator elevator : elevators) {
            elevator.step(step);
        }
    }

    void status(int step) {

        System.out.println("step:" + step + "  %%%%%%%%%%%%%%%%%%%%%");
        for (int floor = this.numberOfFloors - 1; floor >= 0; floor--) {
            System.out.print("floor" + floor + "\t");
            for (Elevator elevator : elevators) {
                if (elevator.currentFloor == floor) {
                    System.out.print("[*] ");

                } else {
                    System.out.print("[ ] ");
                }
            }

            System.out.println("\t" + this.floors.get(floor));
            System.out.println();
        }

        System.out.println(this);

    }

    public String toString() {
        String s = "\n-------------ElevatorSystem------------------\n";
        for (Elevator elevator : this.elevators) {
            s += (elevator);
        }
        s += "\n";
        for (int i = numberOfFloors - 1; i >= 0; i--) {

            String tmp = "Elevator" + floors.get(i).assignedElevator;
            if (floors.get(i).assignedElevator == -1) {
                tmp = "no";
            }

            s += ("Floor" + i + "\t" + "\tpassengers:" + floors.get(i) + "\tassigned_elevator " + tmp + "\n");
        }
        return s;
    }

    public static void log(String string) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(
                    new File("logs.txt"),
                    true /* append = true */));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.write(string);
        writer.write("\n");
        writer.close();
    }

    private int assignElevator(Floor floorToHandle) {

        // Find group of elevators with the smallest workload
        ArrayList<Elevator> winners = new ArrayList<>();
        winners.add(elevators.get(0));
        int workload = elevators.get(0).getWorkload();
        for (int i = 1; i < this.elevators.size(); i++) {
            Elevator elevator = elevators.get(i);
            int workload1 = elevator.getWorkload();
            if (workload1 == workload) {
                winners.add(elevator);
            }
            if (workload1 < workload) {
                winners.clear();
                winners.add(elevator);
            }
        }

        // Find which elevator from the chosen group is the closest
        Elevator grandWinner = winners.get(0);
        int smallestDistance = Floor.getDistance(grandWinner.currentFloor, floorToHandle.id);

        for (Elevator elevator : winners) {
            int distance = Floor.getDistance(elevator.currentFloor, floorToHandle.id);
            if (distance < smallestDistance) {
                grandWinner = elevator;
                smallestDistance = distance;
            }
        }

        return grandWinner.id;
    }
}

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        ElevatorSystem elevatorSystem = new ElevatorSystem(2,5);

        Passenger passenger = new Passenger(3);
//        elevatorSystem.pickup(1,UP);
        Passenger passenger1 = new Passenger(4);

        while(true){
            elevatorSystem.step();
            elevatorSystem.status();
            sleep(1000);
        }
    }
}

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PassengerTest {

    @Test
    void demoTestMethod() {


        ElevatorSystem elevatorSystem = new ElevatorSystem(2,5);

        Passenger passenger = new Passenger(3);
        elevatorSystem.pickup(1,Direction.UP);
        Passenger passenger1 = new Passenger(4);
        elevatorSystem.pickup(5,Direction.DOWN);

        Passenger passenger2 = new Passenger(4);
        elevatorSystem.pickup(1,Direction.UP);

        Passenger passenger3 = new Passenger(4);
        elevatorSystem.pickup(1,Direction.UP);

        Passenger passenger4 = new Passenger(4);
        elevatorSystem.pickup(1,Direction.UP);


        while(true){
            elevatorSystem.step();
            elevatorSystem.status();
            sleep(1000);
        }

        assertTrue(true);
    }
}
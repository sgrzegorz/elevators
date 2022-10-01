import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Path path = FileSystems.getDefault().getPath("logs.txt");
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException x) {
            System.err.println(x);
        }

//        ElevatorSystem elevatorSystem = new ElevatorSystem(2,5);
//
//        Passenger passenger = new Passenger(3);
////        elevatorSystem.pickup(1,UP);
//        Passenger passenger1 = new Passenger(4);
//
//        while(true){
//            elevatorSystem.step();
//            elevatorSystem.status();
//            sleep(1000);
//        }



        ElevatorSystem elevatorSystem = new ElevatorSystem(2,5);
        Generator generator = new Generator(elevatorSystem);
        generator.newPassenger(0,1,3);
        generator.newPassenger(0,4,3);
        generator.newPassenger(0,1,2);
        generator.newPassenger(2,2,4);
        generator.newPassenger(4,2,0);

        generator.newPassenger(5,3,0);

        generator.simulate(20);

    }
}
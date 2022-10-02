import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static java.lang.Thread.sleep;

public class Main {

    static void test1() throws InterruptedException {
        ElevatorSystem elevatorSystem = new ElevatorSystem(2, 5);
        Generator generator = new Generator(elevatorSystem);
        generator.newPassenger(0, 1, 3);
        generator.newPassenger(0, 4, 3);
        generator.newPassenger(0, 1, 2);
        generator.newPassenger(2, 2, 4);
        generator.newPassenger(4, 2, 0);

        generator.newPassenger(5, 3, 0);

        generator.simulate(20);
    }

    static void test2() throws InterruptedException {
        ElevatorSystem elevatorSystem = new ElevatorSystem(2, 4);
        Generator generator = new Generator(elevatorSystem);
        generator.newPassenger(0, 2, 0);
        generator.newPassenger(0, 1, 3);

        generator.simulate(20);
    }

    static void test3() throws InterruptedException {
        ElevatorSystem elevatorSystem = new ElevatorSystem(5, 5);
        Generator generator = new Generator(elevatorSystem);
        generator.newPassenger(0, 2, 0);
        generator.newPassenger(0, 1, 3);
        generator.newPassenger(0, 4, 1);
        generator.newPassenger(0, 3, 0);
        generator.simulate(20);
    }

    static void test4() throws InterruptedException {
        ElevatorSystem elevatorSystem = new ElevatorSystem(5, 5);
        Generator generator = new Generator(elevatorSystem);
        generator.newPassenger(0, 2, 0);
        generator.newPassenger(0, 1, 3);
        generator.newPassenger(1, 4, 1);
        generator.newPassenger(2, 3, 0);
        generator.newPassenger(3, 1, 2);
        generator.newPassenger(3, 2, 4);
        generator.simulate(20);
    }


    public static void main(String[] args) throws InterruptedException {
        // clear logs
        try {
            PrintWriter writer = new PrintWriter("logs.txt");
            writer.print("");
            writer.close();
        } catch (IOException x) {
            System.err.println(x);
        }

        // run simple simulation
        test4();


    }
}
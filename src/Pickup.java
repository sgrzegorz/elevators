import java.util.ArrayList;

public class Pickup {
    int passengerFloor;
    Enum direction;
    ArrayList<Passenger>passengers;

    public Pickup(int passengerFloor, Enum direction) {
        this.passengers = new ArrayList<>();
        this.passengerFloor = passengerFloor;
        this.direction = direction;
    }
}

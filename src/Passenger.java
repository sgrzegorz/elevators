public class Passenger {
    public Passenger(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public Passenger(int destinationFloor,int entryFloor) {
        this.destinationFloor = destinationFloor;
        this.entryFloor = entryFloor;
    }

    int destinationFloor;
    int entryFloor;
}

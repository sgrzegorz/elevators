public class Passenger {
    private static int howMany=0;
    public int id;

    public Passenger(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public Passenger(int destinationFloor,int entryFloor) {
        this.destinationFloor = destinationFloor;
        this.entryFloor = entryFloor;
    }

    public Passenger(int destinationFloor,int entryFloor, int entryStep) {
        this.destinationFloor = destinationFloor;
        this.entryFloor = entryFloor;
        this.entryStep = entryStep;
        this.id = Passenger.howMany;
        Passenger.howMany+=1;
    }

    int destinationFloor;
    int entryFloor;
    int entryStep;

    @Override
    public String toString() {
        return "Passenger"+id;
    }
}

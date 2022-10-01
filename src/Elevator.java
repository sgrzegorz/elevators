public class Elevator {
    int id;
    int currentFloor;
    int targetFloor;


    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public Elevator(int id, int currentFloor, int targetFloor){
        this.currentFloor = currentFloor;
        this.id = id;
        this.targetFloor = targetFloor;

    }
}

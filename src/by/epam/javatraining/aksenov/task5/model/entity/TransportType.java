package by.epam.javatraining.aksenov.task5.model.entity;

public enum TransportType {
    BUS(15), CAR(10), MOTORBIKE(5);

    private int capacity;

    TransportType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

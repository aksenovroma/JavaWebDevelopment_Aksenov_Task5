package by.epam.javatraining.aksenov.task5.model.entity;

import java.util.Objects;

public class FuelTank {
    private int capacity;
    private int fuelVolume;

    public FuelTank(int capacity, int fuelVolume) {
        this.capacity = capacity;
        this.fuelVolume = fuelVolume;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(int fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public boolean checkFuelVolume() {
        return fuelVolume < capacity;
    }

    public void addFuel() {
        fuelVolume++;
    }

    public void subFuel() { fuelVolume--; }

    public boolean isEmpty() { return fuelVolume == 0; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelTank fuelTank = (FuelTank) o;
        return capacity == fuelTank.capacity &&
                fuelVolume == fuelTank.fuelVolume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, fuelVolume);
    }

    @Override
    public String toString() {
        return "capacity=" + capacity +
                ", fuelVolume=" + fuelVolume;
    }
}

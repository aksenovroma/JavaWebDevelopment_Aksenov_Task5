package by.epam.javatraining.aksenov.task5.model.entity;

import by.epam.javatraining.aksenov.task5.model.exception.FuelTankWrongFuelVolumeException;

import java.util.Objects;

public class FuelTank {
    private static final double DEFAULT_CAPACITY = 1.0;
    private static final double DEFAULT_FUEL_VOLUME = 0.0;

    private double capacity = DEFAULT_CAPACITY;
    private double fuelVolume = DEFAULT_FUEL_VOLUME;

    public FuelTank(double capacity, double fuelVolume) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
        if (fuelVolume >= 0) {
            this.fuelVolume = fuelVolume;
        }
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(int fuelVolume) throws FuelTankWrongFuelVolumeException{
        if (fuelVolume < 0) {
            throw new FuelTankWrongFuelVolumeException();
        }
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

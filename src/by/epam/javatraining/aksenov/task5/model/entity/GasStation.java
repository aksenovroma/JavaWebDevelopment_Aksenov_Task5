package by.epam.javatraining.aksenov.task5.model.entity;

import by.epam.javatraining.aksenov.task5.model.exception.GasStationWrongFuelColumnsException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class GasStation {
    private List<FuelColumn> fuelColumns = new LinkedList<>();

    public GasStation(List<FuelColumn> fuelColumns) {
        if (fuelColumns != null) {
            this.fuelColumns = fuelColumns;
        }
    }

    public List<FuelColumn> getFuelColumns() {
        return fuelColumns;
    }

    public void setFuelColumns(List<FuelColumn> fuelColumn) throws GasStationWrongFuelColumnsException {
        if (fuelColumns == null) {
            throw new GasStationWrongFuelColumnsException();
        }
        this.fuelColumns = fuelColumn;
    }

    public void addFuelColumn(FuelColumn fuelColumn) {
        if (fuelColumn != null) {
            fuelColumns.add(fuelColumn);
        }
    }

    public void removeFuelColumn(FuelColumn fuelColumn) {
        if (fuelColumn != null) {
            fuelColumns.remove(fuelColumn);
        }
    }

    public FuelColumn getFuelColumn(int index) {
        if (index >= 0 && index < fuelColumns.size() - 1) {
            return fuelColumns.get(index);
        }
        return null;
    }

    public double getCapacity() {
        double capacity = 0.0;

        for (FuelColumn fuelColumn : fuelColumns) {
            capacity += fuelColumn.getFuelTank().getCapacity();
        }
        return capacity;
    }

    public double getFuelVolume() {
        double fuelVolume = 0.0;

        for (FuelColumn fuelColumn : fuelColumns) {
            fuelVolume += fuelColumn.getFuelTank().getFuelVolume();
        }
        return fuelVolume;
    }

    public FuelColumn getFuelColumnByFuelType(FuelType fuelType) {
        for (FuelColumn fc : fuelColumns) {
            if (fc.getFuelType() == fuelType) {
                return fc;
            }
        }
        return null;
    }

    public int indexOf(FuelColumn fuelColumn) {
        if (fuelColumn != null) {
            return fuelColumns.indexOf(fuelColumn);
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasStation that = (GasStation) o;
        return Objects.equals(fuelColumns, that.fuelColumns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelColumns);
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "fuelColumns=" + fuelColumns +
                '}';
    }
}

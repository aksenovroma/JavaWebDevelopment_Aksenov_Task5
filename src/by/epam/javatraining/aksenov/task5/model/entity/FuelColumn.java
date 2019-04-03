package by.epam.javatraining.aksenov.task5.model.entity;

import by.epam.javatraining.aksenov.task5.model.logic.Refueller;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static properties.ProjectProperties.TIMEOUT_DROVE_TO_STATION;

public class FuelColumn {
    private static final Logger LOGGER = Logger.getLogger(FuelColumn.class);

    private static final String MESSAGE_DROVE_TO_STATION = " drove to the gas station";
    private static final String MESSAGE_LEFT_STATION = " refueled and left ";
    private static final String NEW_LINE = "\n";
    private static final int DEFAULT_FUEL_TANK_CAPACITY = 100;
    private static final int DEFAULT_FUEL_TANK_FUEL_VOLUME = 0;

    private static final FuelTank DEFAULT_FUEL_TANK = new FuelTank(DEFAULT_FUEL_TANK_CAPACITY,
            DEFAULT_FUEL_TANK_FUEL_VOLUME);

    private FuelTank fuelTank = DEFAULT_FUEL_TANK;
    private FuelType fuelType;

    public FuelColumn(FuelTank fuelTank, FuelType fuelType) {
        if (fuelTank != null) {
            this.fuelTank = fuelTank;
        }
        this.fuelType = fuelType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public FuelTank getFuelTank() {
        return fuelTank;
    }

    public void add(Transport transport) {
        if (transport != null) {
            try {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT_DROVE_TO_STATION);
                LOGGER.trace(NEW_LINE + this + NEW_LINE + transport.toString() + MESSAGE_DROVE_TO_STATION);
                Refueller.fuel(transport, fuelTank);
                LOGGER.trace(transport.toString() + MESSAGE_LEFT_STATION + this);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public String toString() {
        return "FuelСolumn{" +
                fuelType + " " +
                fuelTank + '}';
    }
}

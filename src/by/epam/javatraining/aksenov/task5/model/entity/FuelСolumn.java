package by.epam.javatraining.aksenov.task5.model.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class FuelСolumn {
    private static final Logger LOGGER = Logger.getRootLogger();

    private FuelTank fuelTank;
    private FuelType fuelType;

    public FuelСolumn(FuelTank fuelTank, FuelType fuelType) {
        this.fuelTank = fuelTank;
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
            LOGGER.trace(this);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                LOGGER.trace("\n" + transport.toString() + " drove to the gas station");
                while (transport.getFuelTank().checkFuelVolume()) {
                    if (!fuelTank.isEmpty()) {
                        transport.getFuelTank().addFuel();
                        LOGGER.trace(transport.getFuelTank().getFuelVolume() + "(l) fuel in the tank " + transport.toString());
                        TimeUnit.MILLISECONDS.sleep(400);
                        fuelTank.subFuel();
                    } else {
                        LOGGER.trace("fuel is over, " + transport.toString() + " drove away");
                        break;
                    }
                }
                LOGGER.trace(transport.toString() + " refueled and left");
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

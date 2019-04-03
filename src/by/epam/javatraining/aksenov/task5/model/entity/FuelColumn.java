package by.epam.javatraining.aksenov.task5.model.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class FuelColumn {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String MESSAGE_DROVE_TO_STATION = " drove to the gas station";
    private static final String MESSAGE_FUEL = "(l) fuel in the tank ";
    private static final String MESSAGE_FUEL_IS_OVER = "fuel is over, drove away";
    private static final String MESSAGE_LEFT_STATION = " refueled and left ";
    private static final String NEW_LINE = "\n";
    private static final int TIMEOUT_DROVE_TO_STATION = 500;
    private static final int TIMEOUT_FUEL = 400;
    private static final FuelTank DEFAULT_FUEL_TANK = new FuelTank(1.0, 0.0);

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

    public void fuel(Transport transport) {
        if (transport != null) {

            try {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT_DROVE_TO_STATION);
                LOGGER.trace(NEW_LINE + this + NEW_LINE + transport.toString() + MESSAGE_DROVE_TO_STATION);
                while (transport.getFuelTank().checkFuelVolume()) {
                    if (!fuelTank.isEmpty()) {
                        transport.getFuelTank().addFuel();
                        LOGGER.trace(transport.getFuelTank().getFuelVolume() + MESSAGE_FUEL + transport.toString());
                        TimeUnit.MILLISECONDS.sleep(TIMEOUT_FUEL);
                        fuelTank.subFuel();
                    } else {
                        LOGGER.trace(transport.toString() + MESSAGE_FUEL_IS_OVER);
                        break;
                    }
                }
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

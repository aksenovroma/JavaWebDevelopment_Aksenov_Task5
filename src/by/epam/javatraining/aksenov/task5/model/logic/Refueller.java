package by.epam.javatraining.aksenov.task5.model.logic;

import by.epam.javatraining.aksenov.task5.model.entity.FuelTank;
import by.epam.javatraining.aksenov.task5.model.entity.Transport;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static properties.ProjectProperties.TIMEOUT_FUEL;

public class Refueller {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final String MESSAGE_FUEL = "(l) fuel in the tank ";
    private static final String MESSAGE_FUEL_IS_OVER = " fuel is over, drove away";

    public static void fuel(Transport transport, FuelTank fuelTank) throws InterruptedException{
        while (transport.getFuelTank().checkFuelVolume()) {
            if (!fuelTank.isEmpty()) {
                transport.getFuelTank().addFuel();
                LOGGER.trace(transport.getFuelTank().getFuelVolume() + MESSAGE_FUEL + transport.toString());
                TimeUnit.MILLISECONDS.sleep(TIMEOUT_FUEL);
                fuelTank.subFuel();
            } else {
                LOGGER.warn(transport.toString() + MESSAGE_FUEL_IS_OVER);
                break;
            }
        }
    }
}

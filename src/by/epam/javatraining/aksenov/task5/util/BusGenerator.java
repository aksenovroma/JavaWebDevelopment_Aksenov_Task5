package by.epam.javatraining.aksenov.task5.util;

import by.epam.javatraining.aksenov.task5.model.entity.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class BusGenerator {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static TransportType transportType = getRandomTransportType();
    private static FuelType fuelType = getRandomFuelType();

    public static void generate(int busCount) {
        if (busCount > 0) {
            LOGGER.trace("Transport generation");
            FuelСolumn fuelСolumn = new FuelСolumn(new FuelTank(100, 10), fuelType);

            ReentrantLock reentrantLock = new ReentrantLock();

            for (int i = 0; i < busCount; i++) {
                Transport transport = new Transport(getRandomNumber(),
                        transportType, getRandomFuelType(),
                        new FuelTank(transportType.getCapacity(), getRandomFuelVolume()),
                        fuelСolumn, reentrantLock);
                new Thread(transport).start();
            }
        }
    }

    private static String getRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(10000);

        if (number < 1000) {
            number *= 10;
        }
        return number + "-" + (random.nextInt(7) + 1);
    }

    private static TransportType getRandomTransportType() {
        Random random = new Random();
        return TransportType.values()[random.nextInt(TransportType.values().length)];
    }

    private static int getRandomFuelVolume() {
        Random random = new Random();
        return random.nextInt(transportType.getCapacity());
    }

    private static FuelType getRandomFuelType() {
        Random random = new Random();
        return FuelType.values()[random.nextInt(FuelType.values().length)];
    }
}

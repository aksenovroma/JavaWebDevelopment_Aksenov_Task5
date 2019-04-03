package by.epam.javatraining.aksenov.task5.model.entity;

import by.epam.javatraining.aksenov.task5.model.exception.TransportWrongWaitTimeException;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static properties.ProjectProperties.TIMEOUT_TRANSPORT;

public class Transport implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Transport.class);

    private static final int MIN_WAIT_TIME = 6;
    private static final int MAX_WAIT_TIME = 8;

    private static final String DEFAULT_NUMBER = "1234-5";
    private static final String MESSAGE_WAIT = " waiting near fuel column";
    private static final String MESSAGE_LEFT_NOT_ENOUGH_FUEL = " left, not enough fuel";
    private static final String MESSAGE_LEFT_TIME_IS_OVER = " left, waiting time is over\n";
    private static final String NEW_LINE = "\n";

    private String number = DEFAULT_NUMBER;
    private TransportType transportType;
    private FuelType fuelType;
    private int waitTime;
    private FuelTank fuelTank ;
    private GasStation gasStation;
    private ReentrantLock locker;

    public Transport(String number, TransportType transportType,
                     FuelType fuelType, FuelTank fuelTank,
                     GasStation gasStation, ReentrantLock locker) {
        if (number != null) {
            this.number = number;
        }
        if (fuelTank != null) {
            this.fuelTank = fuelTank;
        }
        this.gasStation = gasStation;
        this.transportType = transportType;
        this.fuelType = fuelType;
        this.locker = locker;
        this.waitTime = MIN_WAIT_TIME + new Random().nextInt(MAX_WAIT_TIME - MIN_WAIT_TIME);
    }

    public String getNumber() {
        return number;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) throws TransportWrongWaitTimeException{
        if (waitTime < 0) {
            throw new TransportWrongWaitTimeException();
        }
        this.waitTime = waitTime;
    }

    public FuelTank getFuelTank() { return fuelTank; }

    @Override
    public void run() {
        LOGGER.trace(toString() + MESSAGE_WAIT);

        try {
            FuelColumn fuelColumn = gasStation.getFuelColumnByFuelType(fuelType);

            if (locker.tryLock(waitTime, TimeUnit.SECONDS)) {
                try {
                    if (!fuelColumn.getFuelTank().isEmpty()) {
                        fuelColumn.add(this);
                    }
                    TimeUnit.SECONDS.sleep(TIMEOUT_TRANSPORT);
                } finally {
                    locker.unlock();
                }
            } else if (fuelColumn.getFuelTank().isEmpty()){
                LOGGER.trace(NEW_LINE + toString() + MESSAGE_LEFT_NOT_ENOUGH_FUEL);
            }
            else {
                LOGGER.trace(NEW_LINE + toString() + MESSAGE_LEFT_TIME_IS_OVER);
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public String toString() {
        return "[" + transportType +
                " (" + fuelType + ")" +
                " <" + number + "> " +
                " fuel=" + fuelTank.getFuelVolume() + "]";
    }
}

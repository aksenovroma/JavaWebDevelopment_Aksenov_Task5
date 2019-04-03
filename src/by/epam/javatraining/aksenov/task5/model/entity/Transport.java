package by.epam.javatraining.aksenov.task5.model.entity;

import by.epam.javatraining.aksenov.task5.model.exception.TransportWrongWaitTimeException;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static properties.ProjectProperties.*;

public class Transport implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Transport.class);

    private static final String DEFAULT_NUMBER = "1234-5";
    private static final String MESSAGE_WAIT = " waiting near fuel column";
    private static final String MESSAGE_LEFT_NOT_ENOUGH_FUEL = " left, not enough fuel";
    private static final String MESSAGE_LEFT_TIME_IS_OVER = " left, waiting time is over\n";
    private static final String MESSAGE_ERROR = "Illegal sleepTime";
    private static final String NEW_LINE = "\n";

    private String number = DEFAULT_NUMBER;
    private TransportType transportType;
    private FuelType fuelType;
    private int waitTime;
    private FuelTank fuelTank ;
    private GasStation gasStation;

    public Transport(String number, TransportType transportType,
                     FuelType fuelType, FuelTank fuelTank,
                     GasStation gasStation) {
        if (number != null) {
            this.number = number;
        }
        if (fuelTank != null) {
            this.fuelTank = fuelTank;
        }
        this.gasStation = gasStation;
        this.transportType = transportType;
        this.fuelType = fuelType;
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

            if (gasStation.getLock().tryLock(waitTime, TimeUnit.SECONDS)) {
                try {
                    if (!fuelColumn.getFuelTank().isEmpty()) {
                        fuelColumn.add(this);
                    }
                    TimeUnit.MILLISECONDS.sleep(TIMEOUT_TRANSPORT);
                } finally {
                    gasStation.getLock().unlock();
                }
            } else if (fuelColumn.getFuelTank().isEmpty()){
                LOGGER.warn(NEW_LINE + toString() + MESSAGE_LEFT_NOT_ENOUGH_FUEL);
            }
            else {
                LOGGER.info(NEW_LINE + toString() + MESSAGE_LEFT_TIME_IS_OVER);
            }
        } catch (InterruptedException e) {
            LOGGER.error(MESSAGE_ERROR, e);
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

package by.epam.javatraining.aksenov.task5.model.entity;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Transport implements Runnable{
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final int MIN_WAIT_TIME = 4;
    private static final int MAX_WAIT_TIME = 8;

    private String number;
    private TransportType transportType;
    private FuelType fuelType;
    private FuelTank fuelTank;
    private FuelСolumn fuelСolumn;
    private ReentrantLock locker;
    private int waitTime;

    public Transport(String number, TransportType transportType,
                     FuelType fuelType, FuelTank fuelTank,
                     FuelСolumn fuelСolumn, ReentrantLock locker) {
        this.number = number;
        this.transportType = transportType;
        this.fuelType = fuelType;
        this.fuelTank = fuelTank;
        this.fuelСolumn = fuelСolumn;
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

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public FuelTank getFuelTank() { return fuelTank; }

    @Override
    public void run() {
        LOGGER.trace(toString() + " waiting near fuel column");

        try {
            if (locker.tryLock(waitTime, TimeUnit.SECONDS)) {
                try {
                    if (!fuelСolumn.getFuelTank().isEmpty()) {
                        fuelСolumn.add(this);
                    }
                    TimeUnit.SECONDS.sleep(1);
                } finally {
                    locker.unlock();
                }
            } else if (fuelСolumn.getFuelTank().isEmpty()){
                LOGGER.trace("\n" + toString() + " left, not enough fuel");
            }
            else {
                LOGGER.trace("\n" + toString() + " left, waiting time is over\n");
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

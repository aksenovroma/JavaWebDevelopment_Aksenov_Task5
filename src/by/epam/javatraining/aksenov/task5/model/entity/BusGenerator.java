package by.epam.javatraining.aksenov.task5.model.entity;

import java.util.Random;

public class BusGenerator implements Runnable {
    private BusStop busStop;
    private int busCount;

    public BusGenerator(BusStop busStop, int busCount) {
        this.busStop = busStop;
        this.busCount = busCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < busCount; i++) {
            Thread.currentThread().setName(" Bus generator");
            busStop.add(new Bus(getRandomNumber(), getRandomPassenger()));
        }
    }

    private String getRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(10000);

        if (number < 1000) {
            number *= 10;
        }
        return Integer.toString(number);
    }

    private int getRandomPassenger() {
        Random random = new Random();
        return random.nextInt(11);
    }
}

package by.epam.javatraining.aksenov.task5.controller;

import by.epam.javatraining.aksenov.task5.util.BusGenerator;
import by.epam.javatraining.aksenov.task5.model.logic.BusLoader;
import by.epam.javatraining.aksenov.task5.model.entity.BusStop;

public class Main {
    public static void main(String[] args) {
        BusStop busStop = new BusStop();

        BusGenerator busGenerator = new BusGenerator(busStop, 6);

        BusLoader busLoader1 = new BusLoader(busStop);

        Thread thread1 = new Thread(busGenerator);
        Thread thread2 = new Thread(busLoader1);

        thread1.start();
        thread2.start();

    }
}


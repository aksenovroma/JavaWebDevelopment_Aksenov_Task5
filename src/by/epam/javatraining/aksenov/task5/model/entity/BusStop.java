package by.epam.javatraining.aksenov.task5.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BusStop {
    private static final int MAX_BUS = 3;
    private static final int MIN_BUS = 0;

    private List<Bus> busStop;

    public BusStop() {
        busStop = new CopyOnWriteArrayList<>();
    }

    public synchronized boolean add(Bus bus) {
        try {
            if (busStop.size() < MAX_BUS) {
                notifyAll();
                busStop.add(bus);
                System.out.println(bus + " added");
            } else {
                System.out.println(bus + " wait");
                wait();
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public synchronized Bus get() {

        if (busStop.size() > MIN_BUS) {
            notifyAll();
            for (Bus busFromStop : busStop) {
                System.out.println(busFromStop + " taken");
                busStop.remove(busFromStop);
                return busFromStop;
            }
        }
        System.out.println("busStop is empty");


        return null;
    }
}

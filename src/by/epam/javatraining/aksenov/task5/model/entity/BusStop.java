package by.epam.javatraining.aksenov.task5.model.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BusStop {
    private static final Logger log = Logger.getRootLogger();

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
                log.trace(bus + " drove to a stop");
            } else {
                log.trace(bus + " waiting for a place");
                wait();
                return false;
            }
        } catch (InterruptedException e) {
            log.error(e);
        }
        return true;
    }

    public synchronized Bus get() {

        if (busStop.size() > MIN_BUS) {
            notifyAll();
            for (Bus busFromStop : busStop) {
                log.trace(busFromStop + " started loading");
                busStop.remove(busFromStop);
                return busFromStop;
            }
        }
        log.trace("busStop is empty");

        return null;
    }
}

package by.epam.javatraining.aksenov.task5.model.logic;

import by.epam.javatraining.aksenov.task5.model.entity.Bus;
import by.epam.javatraining.aksenov.task5.model.entity.BusStop;
import org.apache.log4j.Logger;

public class BusLoader implements Runnable {
    private static final Logger log = Logger.getRootLogger();

    private BusStop busStop;

    public BusLoader(BusStop busStop) {
        if (busStop != null) {
            this.busStop = busStop;
        }
    }

    @Override
    public void run() {
        boolean flag = true;

        while (flag) {
            try {
                Thread.sleep(500);
                Bus bus = busStop.get();

                if (bus != null) {
                    while (bus.checkPassenger()) {
                        bus.addPassenger(1);
                        log.trace(bus.getPassenger() + " loaded " + bus);
                    }
                } else {
                    flag = false;
                }
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}

package by.epam.javatraining.aksenov.task5.model.entity;

public class BusLoader implements Runnable {
    private BusStop busStop;

    public BusLoader(BusStop busStop) {
        this.busStop = busStop;
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
                        System.out.println(bus.getPassenger() + " Loaded " + bus);
                    }
                } else {
                    flag = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

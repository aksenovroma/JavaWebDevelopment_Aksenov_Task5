package properties;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    private static final Logger LOGGER = Logger.getRootLogger();

    public static int TIMEOUT_DROVE_TO_STATION;
    public static int TIMEOUT_FUEL;
    public static int TIMEOUT_TRANSPORT;
    public static int FUEL_COLOMN_CAPACITY;
    public static int FUEL_COLOMN_DIESEL_FUEL_VOLUME;
    public static int FUEL_COLOMN_GASOLINE_FUEL_VOLUME;
    public static int TRANSPORT_GENERATED_COUNT;
    public static int MIN_WAIT_TIME;
    public static int MAX_WAIT_TIME;

    public static void loadProperties() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/properties/project.properties");
            property.load(fis);

            TIMEOUT_DROVE_TO_STATION = Integer.parseInt(property.getProperty("timeout.droveToStation"));
            TIMEOUT_FUEL = Integer.parseInt(property.getProperty("timeout.fuel"));
            TIMEOUT_TRANSPORT = Integer.parseInt(property.getProperty("timeout.transport"));

            FUEL_COLOMN_CAPACITY = Integer.parseInt(property.getProperty("fuelColumn.capacity"));
            FUEL_COLOMN_DIESEL_FUEL_VOLUME = Integer.parseInt(property.getProperty("fuelColumn.dieselFuelVolume"));
            FUEL_COLOMN_GASOLINE_FUEL_VOLUME = Integer.parseInt(property.getProperty("fuelColumn.gasolineFuelVolume"));

            TRANSPORT_GENERATED_COUNT = Integer.parseInt(property.getProperty("transport.generatedCount"));

            MIN_WAIT_TIME = Integer.parseInt(property.getProperty("waitTime.min"));
            MAX_WAIT_TIME = Integer.parseInt(property.getProperty("waitTime.max"));

        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}

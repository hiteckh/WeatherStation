package src.org.bluej.WeatherStation.Drivers;

import org.bluej.WeatherStation.Sensors.SensorException;

import java.io.IOException;

/**
 * This class provides a singleton interface to the DS18B20 ground temperature
 * sensor class {@link DS18B20}.
 *
 * @author Joe Reid.
 */
public final class GroundTemperature {

    /**
     * Singleton instance.
     */
    private static DS18B20 instance = null;

    /**
     * @return The singleton.
     */
    public static synchronized DS18B20 getDriver() {
        if (GroundTemperature.instance == null) {
            try {
                GroundTemperature.instance = new DS18B20();
            } catch (final IOException e) {
                throw new SensorException(e);
            }
        }
        return GroundTemperature.instance;
    }

    /**
     * Utility class.
     */
    private GroundTemperature() {
    }
}

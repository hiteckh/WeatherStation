package org.bluej.WeatherStation.Drivers;

import java.io.IOException;

/**
 * This class provides a singleton interface to the DS18B20 ground temperature
 * sensor class {@link DS18B20}.
 *
 * @author Joe Reid.
 */
public class GroundTemperature {
    private static DS18B20 instance = null;

    public static DS18B20 getDriver() throws IOException {
        if (GroundTemperature.instance == null) {

            synchronized (GroundTemperature.class) {
                if (GroundTemperature.instance == null) {
                    GroundTemperature.instance = new DS18B20();
                }
            }
        }

        return GroundTemperature.instance;
    }
}

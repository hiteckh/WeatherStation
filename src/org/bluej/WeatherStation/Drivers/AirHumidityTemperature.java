package org.bluej.WeatherStation.Drivers;

import java.io.IOException;

import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.io.i2c.I2CBus;
import org.bluej.WeatherStation.Sensors.SensorException;

/**
 * This class provides a singleton interface to the HTU21D air humidity and
 * temperature sensor class {@link HTU21D}.
 *
 * @author Joe Reid.
 */
public final class AirHumidityTemperature {

    /**
     * Singleton instance.
     */
    private static HTU21D instance = null;

    /**
     * Utility class.
     */
    private AirHumidityTemperature() {
    }

    /**
     * Gets the singleton driver responsible for the air humidity temperature sensor.
     * @return A {@link HTU21D} driver.
     * @throws IOException Thrown if unable to configure the driver.
     */
    public static synchronized HTU21D getDriver() throws IOException {
        if (AirHumidityTemperature.instance == null) {
            try {
                AirHumidityTemperature.instance = new HTU21D(I2CFactory.getInstance(I2CBus.BUS_1));
            } catch (final UnsupportedBusNumberException e) {
                throw new SensorException(e);
            }
        }
        return AirHumidityTemperature.instance;
    }
}

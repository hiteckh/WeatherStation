package org.kentuni.WeatherStation.Drivers;

import java.io.IOException;

import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CBus;

/**
 * This class provides a singleton interface to the HTU21D air humidity and
 * temperature sensor class {@link HTU21D}.
 *
 * @author Joe Reid.
 */
public class AirHumidityTemperature {
    private static HTU21D instance = null;

    public static HTU21D getDriver() throws IOException {
        if (AirHumidityTemperature.instance == null) {

            synchronized (AirHumidityTemperature.class) {
                if (AirHumidityTemperature.instance == null) {
                    AirHumidityTemperature.instance = new HTU21D(
                        I2CFactory.getInstance (I2CBus.BUS_1)
                    );
                }
            }
        }

        return AirHumidityTemperature.instance;
    }
}

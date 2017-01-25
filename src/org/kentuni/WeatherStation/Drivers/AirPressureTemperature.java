package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CBus;

/**
 * This class provides a singleton interface to the BMP180 air pressure and
 * temperature sensor class {@link BMP180}.
 *
 * @author Joe Reid.
 */
public class AirPressureTemperature {
    private static BMP180 instance = null;

    public static BMP180 getDriver() {
        if (AirPressureTemperature.instance == null) {

            synchronized (AirPressureTemperature.class) {
                if (AirPressureTemperature.instance == null) {
                    AirPressureTemperature.instance = new BMP180(
                        I2CFactory.getInstance (I2CBus.BUS_1),
                        0x77
                    );
                }
            }
        }

        return AirPressureTemperature.instance;
    }
}


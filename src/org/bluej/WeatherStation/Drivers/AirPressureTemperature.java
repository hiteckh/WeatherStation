package org.bluej.WeatherStation.Drivers;

import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import org.bluej.WeatherStation.Sensors.SensorException;

/**
 * This class provides a singleton interface to the BMP180 air pressure and
 * temperature sensor class {@link BMP180}.
 *
 * @author Joe Reid.
 */
public class AirPressureTemperature {

    /**
     * The address of the air pressure and temperature sensor.
     */
    private static final int ADDRESS = 0x77;

    /**
     * The mode of the sensor.
     */
    private static final int MODE = 3;

    /**
     * Singleton instance.
     */
    private static AirPressureTemperature instance = null;

    /**
     * Singleton generator.
     * @return The singleton.
     */
    public static synchronized AirPressureTemperature getInstance() {
        if (AirPressureTemperature.instance == null) {
            try {
                instance = new AirPressureTemperature();
            } catch (final IOException e) {
                throw new SensorException(e);
            }
        }
        return AirPressureTemperature.instance;
    }

    /**
     * The hardware driver.
     */
    private final BMP180 driver;

    /**
     * Default constructor.
     * @throws IOException Thrown if unable to instantiate driver.
     */
    public AirPressureTemperature() throws IOException {
        driver = new BMP180(PinUtil.getI2CBus(I2CBus.BUS_1), ADDRESS);
    }

    /**
     * @return The temperature that the sensor reads.
     */
    public int readTemp() {
        try {
            return driver.read(MODE).getTemperature();
        } catch (final IOException e) {
            throw new SensorException(PinUtil.PI4J_MISSING_EXCEPTION_MESSAGE, e);
        }
    }

    /**
     * @return The air pressure that the sensor reads.
     */
    public int readPressure() {
        try {
            return driver.read(MODE).getPressure();
        } catch (final IOException e) {
            throw new SensorException(PinUtil.PI4J_MISSING_EXCEPTION_MESSAGE, e);
        }
    }
}


package org.bluej.WeatherStation.Drivers;

import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import org.bluej.WeatherStation.Units.Temperature;

/**
 * This class provides a singleton interface to the BMP180 air pressure and
 * temperature sensor class {@link BMP180}.
 *
 * @author Joe Reid.
 */
public class AirPressureTemperature {

    private static final int ADDRESS = 0x77;

    private static final int MODE = 3;

    private static AirPressureTemperature INSTANCE = null;

    public synchronized static AirPressureTemperature getInstance() {
        if (AirPressureTemperature.INSTANCE == null) {
            INSTANCE = new AirPressureTemperature();
        }

        return AirPressureTemperature.INSTANCE;
    }

    private final BMP180 DRIVER;

    public AirPressureTemperature() {
        try {
            DRIVER = new BMP180(PinUtil.getI2CBus(I2CBus.BUS_1), ADDRESS);
        } catch (final IOException e) {
            throw new IllegalStateException(PinUtil.PI4J_MISSING_EXCEPTION_MESSAGE, e);
        }
    }

    public int readTemp() {
        try {
            return DRIVER.read(MODE).getTemperature();
        } catch (final IOException e) {
            throw new IllegalStateException(PinUtil.PI4J_MISSING_EXCEPTION_MESSAGE, e);
        }
    }

    public int readPressure() {
        try {
            return DRIVER.read(MODE).getPressure();
        } catch (final IOException e) {
            throw new IllegalStateException(PinUtil.PI4J_MISSING_EXCEPTION_MESSAGE, e);
        }
    }
}


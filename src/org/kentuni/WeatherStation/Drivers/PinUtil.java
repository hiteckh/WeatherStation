package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.gpio.*;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;

/**
 * Utility class for getting an instance of the GPIO controller.
 */
public final class PinUtil {


	public static String PI4J_MISSING_EXCEPTION_MESSAGE =
			"Please make sure you're running on a Pi with Pi4J installed.";

	private PinUtil() {
		// Utility class.
	}



	/**
	 * Wraps a call to get the GPIO controller instance so that we can give a nicer error message than an
	 * UnsatisfiedLinkError.
	 */
	public static synchronized GpioController getController() {
		try {
			return GpioFactory.getInstance();
		} catch (final UnsatisfiedLinkError e) {
			throw new IllegalStateException(PI4J_MISSING_EXCEPTION_MESSAGE, e);
		}
	}

    /**
     * Wraps a call to get an I2C bus instance so that we can give a nicer error message.
     * @param busNumber The number of the bus we want to grab.
     */
	public static synchronized I2CBus getI2CBus(final int busNumber) {
	    try {
	        return I2CFactory.getInstance(busNumber);
        } catch (final IOException|I2CFactory.UnsupportedBusNumberException e) {
	        throw new IllegalStateException(PI4J_MISSING_EXCEPTION_MESSAGE, e);
        }
    }

}

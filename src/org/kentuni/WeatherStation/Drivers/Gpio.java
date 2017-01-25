package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.gpio.*;

/**
 * Utility class for getting an instance of the GPIO controller.
 */
public final class Gpio {


	private static String PI4J_MISSING_EXCEPTION_MESSAGE =
			"Please make sure you're running on a Pi with Pi4J installed.";

	private Gpio() {
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
}

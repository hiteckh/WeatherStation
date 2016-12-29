package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.gpio.*;

/**
 *
 * Created by harry on 29/12/2016.
 */
public final class Gpio {

	private static Pin PIN_ANEMOMETER = RaspiBcmPin.GPIO_05;


	private static Gpio ourInstance = new Gpio();

	public static Gpio getInstance() {
		return ourInstance;
	}

	private final GpioController controller;

	private final GpioPinDigitalInput gpioPinAnemometer;

	public Gpio() {
		controller = getController();

		gpioPinAnemometer = controller.provisionDigitalInputPin(PIN_ANEMOMETER, PinPullResistance.PULL_UP);
	}



	/**
	 * Wraps a call to get the GPIO controller instance so that we can give a nicer error message than an
	 * UnsatisfiedLinkError.
	 */
	private static synchronized GpioController getController() {
		try {
			return GpioFactory.getInstance();
		} catch (final UnsatisfiedLinkError e) {
			throw new IllegalStateException("Please make sure you're running on a Pi with Pi4J installed.", e);
		}
	}

	/**
	 * Gets the GPIO pin used for the anemometer. Thread safe, in that multiple objects can listen to the anemometer
	 * pin without interfering with one another.
	 * @return The Anemometer GPIO pin.
	 */
	public GpioPinDigitalInput getAnemometerPin() {
		return gpioPinAnemometer;
	}
}

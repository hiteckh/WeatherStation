package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Singleton class for interacting with the anemometer.
 */
public final class Anemometer {

	private static Anemometer INSTANCE = null;

	public static synchronized Anemometer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Anemometer();
		}

		return INSTANCE;
	}

	private static Pin PIN_ANEMOMETER = RaspiBcmPin.GPIO_05;

	private final GpioPinDigitalInput gpioPinAnemometer;

	/**
	 * Constructor.
	 * Grabs the GpioController and sets the pin state for the anemometer.
	 */
	private Anemometer() {
		final GpioController controller = Gpio.getController();
		gpioPinAnemometer = controller.provisionDigitalInputPin(PIN_ANEMOMETER, PinPullResistance.PULL_UP);
	}

	/**
	 * Adds a listener to the anemometer.
	 * @param listener The listener to add.
	 */
	public void addListener(final AnemometerListener listener) {
		gpioPinAnemometer.addListener((GpioPinListenerDigital) event -> listener.onTriggered());
	}
}


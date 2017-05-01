package org.bluej.WeatherStation.Drivers;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Singleton class for interacting with the anemometer.
 */
public final class Anemometer {

	/**
	 * Singleton instance.
	 */
	private static Anemometer instance = null;

	/**
	 * @return The singleton.
	 */
	public static synchronized Anemometer getInstance() {
		if (instance == null) {
			instance = new Anemometer();
		}
		return instance;
	}

	/**
	 * The pin of the anemometer.
	 * Decided at compile time.
	 */
	private static final Pin PIN_ANEMOMETER = RaspiBcmPin.GPIO_21;

	/**
	 * The GPIO pin of the anemometer.
	 * Provisioned at runtime.
	 */
	private final GpioPinDigitalInput gpioPinAnemometer;

	/**
	 * Constructor.
	 * Grabs the GpioController and sets the pin state for the anemometer.
	 */
	private Anemometer() {
		final GpioController controller = PinUtil.getController();
		gpioPinAnemometer = controller.provisionDigitalInputPin(PIN_ANEMOMETER, PinPullResistance.PULL_UP);
	}

	/**
	 * Adds a listener to the anemometer.
	 * @param listener The listener to add.
	 */
	public void addListener(final AnemometerListener listener) {
		gpioPinAnemometer.addListener((GpioPinListenerDigital) event -> {
			if (event.getState() == PinState.LOW)
				listener.onTriggered();
		});
	}
}


package org.bluej.WeatherStation.Drivers;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Singleton class for interacting with the rain sensor.
 */
public final class RainMeter {

	/**
	 * Singleton instance.
	 */
	private static RainMeter instance = null;

	/**
	 * @return The singleton.
	 */
	public static synchronized RainMeter getInstance() {
	    if (instance == null) {
	        instance = new RainMeter();
        }
        return instance;
    }

	/**
	 * The pin of the rain sensor.
	 * Decided at compile time.
	 */
	private static final Pin PIN_RAIN_SENSOR = RaspiBcmPin.GPIO_22;

	/**
	 * The GPIO pin of the rain sensor.
	 * Decided at runtime.
	 */
    private final GpioPinDigitalInput gpioPinDigitalInput;

    /**
     * Constructor.
     * Grabs the GpioController and sets the pin state for the rain sensor.
     */
	private RainMeter() {
		final GpioController controller = PinUtil.getController();
		gpioPinDigitalInput = controller.provisionDigitalInputPin(PIN_RAIN_SENSOR);
	}

    /**
     * Adds a listener to the rain sensor.
     * @param listener The listener to add.
     */
	public void addListener(final RainMeterListener listener) {
	    gpioPinDigitalInput.addListener((GpioPinListenerDigital) event -> {
			if (event.getState() == PinState.LOW)
				listener.onTriggered();
		});
    }


}

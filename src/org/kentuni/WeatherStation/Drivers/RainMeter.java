package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.kentuni.WeatherStation.Implementations.PiRainSensor;

/**
 * Singleton class for interacting with the rain sensor.
 */
public final class RainMeter {

	private static RainMeter INSTANCE = null;

	public static synchronized RainMeter getInstance() {
	    if (INSTANCE == null) {
	        INSTANCE = new RainMeter();
        }

        return INSTANCE;
    }

    private static Pin PIN_RAIN_SENSOR = RaspiBcmPin.GPIO_06;

    private final GpioPinDigitalInput GPIO_PIN_RAIN_SENSOR;

    /**
     * Constructor.
     * Grabs the GpioController and sets the pin state for the rain sensor.
     */
	private RainMeter() {
		final GpioController controller = Gpio.getController();
		GPIO_PIN_RAIN_SENSOR = controller.provisionDigitalInputPin(PIN_RAIN_SENSOR);
	}

    /**
     * Adds a listener to the rain sensor.
     * @param listener The listener to add.
     */
	public void addListener(final RainMeterListener listener) {
	    GPIO_PIN_RAIN_SENSOR.addListener((GpioPinListenerDigital) event -> listener.onTriggered());
    }


}

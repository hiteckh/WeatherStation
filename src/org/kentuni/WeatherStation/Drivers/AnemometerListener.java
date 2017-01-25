package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;

/**
 * A listener to the {@link Anemometer}.
 */
public interface AnemometerListener {

	/**
	 * Called when the anemometer spins half way around.
	 */
	void onTriggered();
}

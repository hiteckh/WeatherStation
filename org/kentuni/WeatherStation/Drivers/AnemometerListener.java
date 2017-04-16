package org.kentuni.WeatherStation.Drivers;

/**
 * A listener to the {@link Anemometer}.
 */
public interface AnemometerListener {

	/**
	 * Called when the anemometer spins half way around.
	 */
	void onTriggered();
}

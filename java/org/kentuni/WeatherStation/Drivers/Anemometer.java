package org.kentuni.WeatherStation.Drivers;

import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.kentuni.WeatherStation.Sensors.WindSpeed;

import java.util.function.Consumer;

/**
 * Measure wind speed from the weather station.
 * Created by harry on 29/12/2016.
 */
public class Anemometer {

	private static double CIRCLE_RADIUS_CM = 9;

	private int count = 0;

	private long lastCollectionTimeMillis;

	private Timer<WindSpeed> timer;

	public Anemometer() {
		Gpio.getInstance().getAnemometerPin().addListener(
				(GpioPinListenerDigital) event -> count++
		);
		lastCollectionTimeMillis = System.currentTimeMillis();
	}

	public Anemometer(final Consumer<WindSpeed> listener, final int sampleTime) {
		this();
		this.timer = new Timer<>(listener, this::getWindSpeed, sampleTime);
	}

	/**
	 * Stops automatic updates from occurring.
	 */
	public void stopUpdates() {
		if (timer != null)
			timer.stop();
		timer = null;
	}

	/**
	 *
	 * @return The wind speed.
	 */
	public WindSpeed getWindSpeed() {
		final long timePassedMillis = System.currentTimeMillis() - lastCollectionTimeMillis;

		final double speedPerSec = count / (timePassedMillis / 1000f);
		return new WindSpeed(count / 2, CIRCLE_RADIUS_CM, timePassedMillis);
	}


}

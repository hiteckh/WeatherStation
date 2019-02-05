package src.org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.AirQualitySensor;
import org.bluej.WeatherStation.Units.AirQuality;

import java.util.Random;

/**
 * Mock implementation of the air quality sensor.
 */
public class MockAirQualitySensor implements AirQualitySensor {

	/**
	 * Random number gen.
	 */
	private final Random random;

	/**
	 * Default constructor.
	 */
	public MockAirQualitySensor() {
		this.random = new Random();
	}

	/**
	 * Chooses an air quality percentage as an evenly distributed random number.
	 * @return A random air quality.
	 * @see Random#nextDouble()
	 */
	@Override
	public AirQuality getAirQuality() {
		final double percentage = random.nextDouble();
		return new AirQuality(percentage);
	}
}

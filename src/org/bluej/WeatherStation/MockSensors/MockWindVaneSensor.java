package src.org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.WindVaneSensor;
import org.bluej.WeatherStation.Units.WindDirection;

import java.util.Random;

/**
 * Mock implementation of the wind vane.
 */
public class MockWindVaneSensor implements WindVaneSensor {

	/**
	 * Random number generator.
	 */
	private final Random random;

	/**
	 * Constructor.
	 */
	public MockWindVaneSensor() {
		this.random = new Random();
	}

	/**
	 * {@inheritDoc}
	 * @return New random direction each time, uniformly distributed.
	 * @see Random#nextDouble()
	 */
	@Override
	public WindDirection getWindDirection() {
		final double angle = random.nextDouble() * 365d;
		return new WindDirection(angle);
	}
}

package org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.WindVaneSensor;
import org.bluej.WeatherStation.Units.WindDirection;

import java.util.Random;

public class MockWindVaneSensor implements WindVaneSensor {

	private final Random random;

	public MockWindVaneSensor() {
		this.random = new Random();
	}

	@Override
	public WindDirection getWindDirection() {
		final double angle = random.nextDouble() * 365;
		return new WindDirection(angle);
	}
}

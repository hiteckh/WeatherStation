package org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.AirQualitySensor;
import org.bluej.WeatherStation.Units.AirQuality;

import java.util.Random;

public class MockAirQualitySensor implements AirQualitySensor {

	final Random random;

	public MockAirQualitySensor() {
		this.random = new Random();
	}

	@Override
	public AirQuality getAirQuality() {
		final double percentage = random.nextDouble();
		return new AirQuality(percentage);
	}
}

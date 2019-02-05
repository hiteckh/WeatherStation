package src.org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.AirQuality;

/**
 * Represents an air quality sensor.
 */
public interface AirQualitySensor {

	/**
	 * @return The current air quality.
	 */
	AirQuality getAirQuality();
}

package src.org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.WindSpeed;

/**
 * Represents the wind speed.
 */
public interface WindSpeedSensor {

    /**
     * @return The current wind speed.
     */
    WindSpeed getWindSpeed();
}

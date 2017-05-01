package org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.Humidity;

/**
 * Represents the humidity sensor.
 */
public interface HumiditySensor {
    /**
     * @return The humidity.
     */
    Humidity getHumidity();
}

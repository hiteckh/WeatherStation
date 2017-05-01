package org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.RainFall;

/**
 * Represents the rainfall sensor.
 */
public interface RainSensor {

    /**
     * @return The rainfall.
     */
    RainFall getRainfall();
}

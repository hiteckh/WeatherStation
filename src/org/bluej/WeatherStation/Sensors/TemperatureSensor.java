package org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.Temperature;

/**
 * Represents the temperature sensor.
 */
public interface TemperatureSensor {

    /**
     * @return The current temperature.
     */
    Temperature getTemperature();
}


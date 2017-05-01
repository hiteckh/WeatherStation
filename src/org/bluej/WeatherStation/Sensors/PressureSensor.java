package org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.Pressure;

/**
 * Represents the pressure sensor.
 */
public interface PressureSensor {

    /**
     * @return The current pressure.
     */
    Pressure getPressure();
}

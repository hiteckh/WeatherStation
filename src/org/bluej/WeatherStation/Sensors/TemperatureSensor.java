package org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.Temperature;
import org.bluej.WeatherStation.Sensors.SensorError;

public interface TemperatureSensor {
    public Temperature getTemperature() throws SensorError;
}


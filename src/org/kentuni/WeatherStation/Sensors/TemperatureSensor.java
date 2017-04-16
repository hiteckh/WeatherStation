package org.kentuni.WeatherStation.Sensors;

import org.kentuni.WeatherStation.Units.Temperature;
import org.kentuni.WeatherStation.Sensors.SensorError;

public interface TemperatureSensor {
    public Temperature getTemperature() throws SensorError;
}


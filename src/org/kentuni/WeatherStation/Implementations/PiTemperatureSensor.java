package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Units.Temperature;
import org.kentuni.WeatherStation.Sensors.TemperatureSensor;

class PiTemperatureSensor implements TemperatureSensor {
    public PiTemperatureSensor() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This sensor is not implemented");
    }

    public Temperature getTemperature() {
        return null;
    }
}


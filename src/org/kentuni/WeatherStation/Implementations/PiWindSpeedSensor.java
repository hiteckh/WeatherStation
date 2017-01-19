package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Units.WindSpeed;
import org.kentuni.WeatherStation.Sensors.WindSpeedSensor;

class PiWindSpeedSensor implements WindSpeedSensor {
    public PiWindSpeedSensor() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This sensor is not implemented");
    }

    public WindSpeed getWindSpeed() {
        return null;
    }
}

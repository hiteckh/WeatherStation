package org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Units.Pressure;
import org.bluej.WeatherStation.Sensors.PressureSensor;
import org.bluej.WeatherStation.Drivers.AirPressureTemperature;

import java.io.IOException;

class PiPressureSensor implements PressureSensor {

    private final AirPressureTemperature SENSOR;

    public PiPressureSensor() throws IOException {
        SENSOR = AirPressureTemperature.getInstance();
    }

    public Pressure getPressure() {
        final int rawPressure = SENSOR.readPressure();
        return new Pressure(rawPressure);
    }
}

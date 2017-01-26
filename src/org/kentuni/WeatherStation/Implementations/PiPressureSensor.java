package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Units.Pressure;
import org.kentuni.WeatherStation.Sensors.PressureSensor;
import org.kentuni.WeatherStation.Drivers.AirPressureTemperature;

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

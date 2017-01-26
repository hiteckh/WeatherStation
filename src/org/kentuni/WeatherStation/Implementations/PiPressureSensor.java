package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Units.Pressure;
import org.kentuni.WeatherStation.Sensors.PressureSensor;
import org.kentuni.WeatherStation.Drivers.AirPressureTemperature;

import java.io.IOException;

class PiPressureSensor  implements PressureSensor {
    public PiPressureSensor() throws IOException {
        try {
            AirPressureTemperature.getInstance().setMode(3);
        } catch (IOException e) {
            throw e;
        }
    }

    public Pressure getPressure() {
        int rawPressure = AirPressureTemperature.getInstance()
            .read().getPressure(); //atmospheres?
        return new Pressure(rawPressure);
    }
}

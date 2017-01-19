package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Units.Pressure;
import org.kentuni.WeatherStation.Sensors.PressureSensor;
import org.kentuni.WeatherStation.Drivers.BMP180;

import java.io.IOException;

class PiPressureSensor  implements PressureSensor {
    public PiPressureSensor() throws IOException {
        try {
            BMP180.INSTANCE.setMode(3);
        } catch (IOException e) {
            throw e;
        }
    }

    public Pressure getPressure() {
        int rawPressure = BMP180.INSTANCE.read().getPressure(); //atmospheres?
        return new Pressure(rawPressure);
    }
}

package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Units.Humidity;
import org.kentuni.WeatherStation.Sensors.HumiditySensor;

class PiHumiditySensor implements HumiditySensor {
    public PiHumiditySensor() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This sensor is not implemented");
    }

    public Humidity getHumidity() {
        return null;
    }
}

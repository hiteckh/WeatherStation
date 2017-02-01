package org.kentuni.WeatherStation.Sensors;

import org.kentuni.WeatherStation.Units.Humidity;
import org.kentuni.WeatherStation.Sensors.SensorError;

public interface HumiditySensor {
    public Humidity getHumidity() throws SensorError;
}

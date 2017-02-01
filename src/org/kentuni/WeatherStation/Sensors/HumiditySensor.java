package org.kentuni.WeatherStation.Sensors;

import org.kentuni.WeatherStation.Units.Humidity;
import org.kentuni.WeatherStation.Sensors.HumiditySensorError;

public interface HumiditySensor {
    public Humidity getHumidity() throws HumiditySensorError;
}

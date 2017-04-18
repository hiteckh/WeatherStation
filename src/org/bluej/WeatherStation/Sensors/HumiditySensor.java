package org.bluej.WeatherStation.Sensors;

import org.bluej.WeatherStation.Units.Humidity;
import org.bluej.WeatherStation.Sensors.SensorError;

public interface HumiditySensor {
    public Humidity getHumidity() throws SensorError;
}

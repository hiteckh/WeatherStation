package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Drivers.AirHumidityTemperature;
import org.kentuni.WeatherStation.Drivers.HTU21D;

import org.kentuni.WeatherStation.Units.Humidity;
import org.kentuni.WeatherStation.Sensors.HumiditySensor;
import org.kentuni.WeatherStation.Sensors.HumiditySensorError;


class PiHumiditySensor implements HumiditySensor {
    private HTU21D sensor;

    public PiHumiditySensor() throws HumiditySensorError {
        try {
            this.sensor = AirHumidityTemperature.getDriver();
        } catch (Exception e) {
            throw new HumiditySensorError(e);
        }
    }

    public Humidity getHumidity() throws HumiditySensorError {
        try {
            return new Humidity(this.sensor.read().getHumidity());
        } catch (Exception e) {
            throw new HumiditySensorError(e);
        }
    }
}

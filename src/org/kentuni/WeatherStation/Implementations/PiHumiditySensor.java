package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Drivers.AirHumidityTemperature;
import org.kentuni.WeatherStation.Drivers.HTU21D;

import org.kentuni.WeatherStation.Units.Humidity;
import org.kentuni.WeatherStation.Sensors.HumiditySensor;
import org.kentuni.WeatherStation.Sensors.SensorError;


class PiHumiditySensor implements HumiditySensor {
    private HTU21D sensor;

    public PiHumiditySensor() throws SensorError {
        try {
            this.sensor = AirHumidityTemperature.getDriver();
        } catch (Exception e) {
            throw new SensorError(e);
        }
    }

    public Humidity getHumidity() throws SensorError {
        try {
            return new Humidity(this.sensor.read().getHumidity()/100);
        } catch (Exception e) {
            throw new SensorError(e);
        }
    }
}

package org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Drivers.AirHumidityTemperature;
import org.bluej.WeatherStation.Drivers.HTU21D;

import org.bluej.WeatherStation.Units.Humidity;
import org.bluej.WeatherStation.Sensors.HumiditySensor;
import org.bluej.WeatherStation.Sensors.SensorError;


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

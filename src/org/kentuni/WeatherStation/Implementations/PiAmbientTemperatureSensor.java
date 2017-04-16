package org.kentuni.WeatherStation.Implementations;


import org.kentuni.WeatherStation.Drivers.AirHumidityTemperature;
import org.kentuni.WeatherStation.Drivers.HTU21D;

import org.kentuni.WeatherStation.Units.Temperature;
import org.kentuni.WeatherStation.Sensors.TemperatureSensor;
import org.kentuni.WeatherStation.Sensors.SensorError;

class PiAmbientTemperatureSensor implements TemperatureSensor {
    private HTU21D sensor;

    public PiAmbientTemperatureSensor() throws SensorError {
        try {
            this.sensor = AirHumidityTemperature.getDriver();
        } catch (Exception e) {
            throw new SensorError(e);
        }
    }

    public Temperature getTemperature() throws SensorError {
        try {
            return new Temperature(Temperature.TemperatureUnit.CELSIUS, this.sensor.read().getTemperature());
        } catch (Exception e) {
            throw new SensorError(e);
        }
    }
}


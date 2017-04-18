package org.bluej.WeatherStation.Implementations;


import org.bluej.WeatherStation.Drivers.AirHumidityTemperature;
import org.bluej.WeatherStation.Drivers.HTU21D;

import org.bluej.WeatherStation.Units.Temperature;
import org.bluej.WeatherStation.Sensors.TemperatureSensor;
import org.bluej.WeatherStation.Sensors.SensorError;

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


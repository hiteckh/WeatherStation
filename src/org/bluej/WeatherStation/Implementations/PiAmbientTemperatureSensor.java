package org.bluej.WeatherStation.Implementations;


import org.bluej.WeatherStation.Drivers.AirHumidityTemperature;
import org.bluej.WeatherStation.Drivers.HTU21D;

import org.bluej.WeatherStation.Units.Temperature;
import org.bluej.WeatherStation.Sensors.TemperatureSensor;
import org.bluej.WeatherStation.Sensors.SensorException;

import java.io.IOException;

/**
 * Implementation of the ambient temperature driver.
 * Uses {@link AirHumidityTemperature}.
 */
public class PiAmbientTemperatureSensor implements TemperatureSensor {

    /**
     * Instance of the driver.
     */
    private HTU21D driver;

    /**
     * Default constructor.
     */
    public PiAmbientTemperatureSensor() {
        try {
            this.driver = AirHumidityTemperature.getDriver();
        } catch (final IOException e) {
            throw new SensorException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Temperature getTemperature() {
        try {
            return new Temperature(Temperature.TemperatureUnit.CELSIUS, this.driver.read().getTemperature());
        } catch (Exception e) {
            throw new SensorException(e);
        }
    }
}


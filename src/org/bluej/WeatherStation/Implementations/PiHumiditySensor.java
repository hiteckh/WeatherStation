package org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Drivers.AirHumidityTemperature;
import org.bluej.WeatherStation.Drivers.HTU21D;

import org.bluej.WeatherStation.Units.Humidity;
import org.bluej.WeatherStation.Sensors.HumiditySensor;
import org.bluej.WeatherStation.Sensors.SensorException;

/**
 * Implementation of the humidity sensor.
 * Uses {@link HTU21D}.
 */
public class PiHumiditySensor implements HumiditySensor {

    /**
     * Driver instance.
     */
    private final HTU21D sensor;

    /**
     * Default constructor.
     */
    public PiHumiditySensor() {
        try {
            this.sensor = AirHumidityTemperature.getDriver();
        } catch (Exception e) {
            throw new SensorException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Humidity getHumidity() {
        try {
            return new Humidity(this.sensor.read().getHumidity() / Humidity.PERCENTAGE_COEFFICIENT);
        } catch (Exception e) {
            throw new SensorException(e);
        }
    }
}

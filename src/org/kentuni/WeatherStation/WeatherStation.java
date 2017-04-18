package org.kentuni.WeatherStation;

import org.kentuni.WeatherStation.Sensors.*;
import org.kentuni.WeatherStation.Implementations.Factory;
import org.kentuni.WeatherStation.Implementations.Platform;

/**
 * A class providing a high level interface to a weather station device.
 *
 * @author  Joe Reid
 */
public class WeatherStation {
    /**{@link HumiditySensor} instance built for the weather station.*/
    private final HumiditySensor humiditySensor;

    /**{@link PressureSensor} instance built for the weather station.*/
    private final PressureSensor pressureSensor;

    /**{@link TemperatureSensor} instance built for the weather station.*/
    private final TemperatureSensor ambientTemperatureSensor;

    /**{@link WindSpeedSensor} instance built for the weather station.*/
    private final WindSpeedSensor windSpeedSensor;

    /**
     * The sole constructor for a {@link WeatherStation} object.
     * The constructor builds the object for the correct platform and provides
     * simple methods to access live sensor values.
     */
    public WeatherStation() {
        // set up a sensor factory for this platform
        Factory wsFactory = new Factory(Platform.WEATHERSTATION_V1);

        // populate the internal sensor variables
        this.humiditySensor = wsFactory.getHumiditySensor();
        this.pressureSensor = wsFactory.getPressureSensor();
        this.ambientTemperatureSensor = wsFactory.getAmbientTemperatureSensor();
        this.windSpeedSensor = wsFactory.getWindSpeedSensor();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link HumiditySensor} in percent.
     *
     * @return The current humidity in percent
     * @see HumiditySensor
     */
    public final double getHumidity() throws SensorError {
        return this.humiditySensor.getHumidity().inPercent();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link PressureSensor} in millibars.
     *
     * @return The current pressure in millibars
     * @see PressureSensor
     */
    public final double getPressure() {
        return this.pressureSensor.getPressure().inMillibars();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link TemperatureSensor} in celsius.
     *
     * @return The current temperature in celsius
     * @see TemperatureSensor
     */
    public final double getAmbientTemperature() throws SensorError {
        return this.ambientTemperatureSensor.getTemperature().inCelsius();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link WindSpeedSensor} in kilometers per hour.
     *
     * @return The current wind speed in kilometers per hour
     * @see WindSpeedSensor
     */
    public final double getWindSpeed() {
        return this.windSpeedSensor.getWindSpeed().inKilometersPerHour();
    }
}


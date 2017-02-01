package org.kentuni.WeatherStation;

import org.kentuni.WeatherStation.Sensors.SensorError;
import org.kentuni.WeatherStation.Sensors.HumiditySensor;
import org.kentuni.WeatherStation.Sensors.PressureSensor;
import org.kentuni.WeatherStation.Sensors.TemperatureSensor;
import org.kentuni.WeatherStation.Sensors.WindSpeedSensor;

import org.kentuni.WeatherStation.Implementations.Factory;
import org.kentuni.WeatherStation.Implementations.Platform;

/**
 * A class providing a high level interface to a weather station device.
 *
 * @author  Joe Reid
 */
public class SimpleWeatherStation {
    /**{@link HumiditySensor} instance built for the weather station.*/
    private final HumiditySensor humiditySensor;

    /**{@link PressureSensor} instance built for the weather station.*/
    private final PressureSensor pressureSensor;

    /**{@link TemperatureSensor} instance built for the weather station.*/
    private final TemperatureSensor ambientTemperatureSensor;

    /**{@link WindSpeedSensor} instance built for the weather station.*/
    private final WindSpeedSensor windSpeedSensor;

    /**
     * The sole constructor for a {@link SimpleWeatherStation} object.
     * The constructor builds the object for the correct platform and provides
     * simple methods to access live sensor values.
     */
    public SimpleWeatherStation() {
        // set up a sensor factory for this platform
        Factory wsFactory = new Factory(Platform.MOCK);

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
    public final double getPercentHumidity() throws SensorError {
        return this.humiditySensor.getHumidity().inPercent();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link PressureSensor} in millibars.
     *
     * @return The current pressure in millibars
     * @see PressureSensor
     */
    public final double getMillibarPressure() {
        return this.pressureSensor.getPressure().inMillibars();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link TemperatureSensor} in celsius.
     *
     * @return The current temperature in celsius
     * @see TemperatureSensor
     */
    public final double getCelsiusAmbientTemperature() throws SensorError {
        return this.ambientTemperatureSensor.getTemperature().inCelsius();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link WindSpeedSensor} in kilometers per hour.
     *
     * @return The current wind speed in kilometers per hour
     * @see WindSpeedSensor
     */
    public final double getKilometersPerHourWindSpeed() {
        return this.windSpeedSensor.getWindSpeed().inKilometersPerHour();
    }
}


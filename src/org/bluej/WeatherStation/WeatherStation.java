package src.org.bluej.WeatherStation;

import org.bluej.WeatherStation.Sensors.*;
import org.bluej.WeatherStation.Implementations.Factory;
import org.bluej.WeatherStation.Implementations.Platform;
     
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

    /**{@link WindVaneSensor} instance built for the weather station.*/
    private final WindVaneSensor windVaneSensor;

    /**{@link AirQualitySensor} instance built for the weather station.*/
    private final AirQualitySensor airQualitySensor;

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
        this.windVaneSensor = wsFactory.getWindVaneSensor();
        this.airQualitySensor = wsFactory.getAirQualitySensor();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link HumiditySensor} in percent.
     *
     * @return The current humidity in percent
     * @see HumiditySensor
     * @throws SensorException Thrown when we can't read reliably from the sensor, for whatever reason.
     */
    public final double getHumidity() throws SensorException {
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
     * @throws SensorException Thrown when we can't read reliably from the sensor, for whatever reason.
     */
    public final double getAmbientTemperature() throws SensorException {
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

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link WindVaneSensor} in degrees.
     *
     * @return The current wind direction, in degrees.
     * @see WindVaneSensor
     */
    public final double getWindDirection() {
        return windVaneSensor.getWindDirection().getDegrees();
    }

    /**
     * This method returns the most up to date sensor data from the internal
     * {@link AirQualitySensor} as a percentage.
     * @return The current air quality, as a percentage.
     * @see AirQualitySensor
     */
    public final double getAirQuality() {
        return airQualitySensor.getAirQuality().getPercentage();
    }
}


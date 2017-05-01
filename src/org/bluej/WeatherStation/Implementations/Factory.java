package org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.MockSensors.MockAirQualitySensor;
import org.bluej.WeatherStation.MockSensors.MockHumiditySensor;
import org.bluej.WeatherStation.MockSensors.MockPressureSensor;
import org.bluej.WeatherStation.MockSensors.MockTemperatureSensor;
import org.bluej.WeatherStation.MockSensors.MockWindSpeedSensor;
import org.bluej.WeatherStation.MockSensors.MockWindVaneSensor;
import org.bluej.WeatherStation.Sensors.AirQualitySensor;
import org.bluej.WeatherStation.Sensors.HumiditySensor;
import org.bluej.WeatherStation.Sensors.PressureSensor;
import org.bluej.WeatherStation.Sensors.TemperatureSensor;
import org.bluej.WeatherStation.Sensors.WindSpeedSensor;
import org.bluej.WeatherStation.Sensors.WindVaneSensor;


/**
 * A class for building the sensor set for a given target {@link Platform}.
 *
 * @author  Joe Reid
 * @see Platform
 * @see HumiditySensor
 * @see PressureSensor
 * @see TemperatureSensor
 * @see WindSpeedSensor
 */
public class Factory {
    /**{@link HumiditySensor} instance built for the {@link Platform} supplied
     * to the constructor.*/
    private final HumiditySensor humiditySensor;

    /**{@link PressureSensor} instance built for the {@link Platform} supplied
     * to the constructor.*/
    private final PressureSensor pressureSensor;

    /**{@link TemperatureSensor} instance built for the {@link Platform}
     * supplied to the constructor.*/
    private final TemperatureSensor ambientTemperatureSensor;

    /**{@link WindSpeedSensor} instance built for the {@link Platform} supplied
     * to the constructor.*/
    private final WindSpeedSensor windSpeedSensor;

    /**
     * {@link WindVaneSensor} instance built for the {@link Platform} supplied
     * to the constructor.
     */
    private final WindVaneSensor windVaneSensor;

    /**
     * {@link AirQualitySensor} instance built for the {@link Platform} supplied
     * to the constructor.
     */
    private final AirQualitySensor airQualitySensor;

    /**
     * The sole constructor for a {@link Factory} object.
     * The platform argument must specify which {@link Platform} target they
     * wish the {@link Factory} to build for.
     * <p>
     * This constructor will throw a {@link UnsupportedOperationException} when
     * the {@link Platform} target is not supported, or when any of the sensors
     * failed to initialize and threw an exception themselves.
     *
     * @throws UnsupportedOperationException when given an unsuported platform
     * or initialization of a platform fails.
     *
     * @param platform an enum type representing the desired platform target.
     * @see Platform
     */
    public Factory(final Platform platform) throws
        UnsupportedOperationException {

        switch (platform) {
            case WEATHERSTATION_V1:
                try {
                    this.humiditySensor = new PiHumiditySensor();
                    this.pressureSensor = new PiPressureSensor();
                    this.ambientTemperatureSensor = new PiAmbientTemperatureSensor();
                    this.windSpeedSensor = new PiWindSpeedSensor();
                    this.windVaneSensor = new PiWindVaneSensor();
                    this.airQualitySensor = new PiAirQualitySensor();
                } catch (Exception e) {
                    throw new UnsupportedOperationException(
                        "The provided platform is supported but failed to "
                        + "initialize"
                    );
                }
                break;

            case MOCK:
                this.humiditySensor = new MockHumiditySensor();
                this.pressureSensor = new MockPressureSensor();
                this.ambientTemperatureSensor = new MockTemperatureSensor();
                this.windSpeedSensor = new MockWindSpeedSensor();
                this.windVaneSensor = new MockWindVaneSensor();
                this.airQualitySensor = new MockAirQualitySensor();
                break;

            default:
                throw new UnsupportedOperationException(
                    "The provided platform is not supported"
                );
        }
    }

    /**
     * A getter method for the {@link HumiditySensor} appropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link HumiditySensor} appropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see HumiditySensor
     */
    public final HumiditySensor getHumiditySensor() {
        return this.humiditySensor;
    }

    /**
     * A getter method for the {@link PressureSensor} appropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link PressureSensor} appropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see PressureSensor
     */
    public final PressureSensor getPressureSensor() {
        return this.pressureSensor;
    }

    /**
     * A getter method for the {@link TemperatureSensor} appropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link TemperatureSensor} appropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see TemperatureSensor
     */
    public final TemperatureSensor getAmbientTemperatureSensor() {
        return this.ambientTemperatureSensor;
    }

    /**
     * A getter method for the {@link WindSpeedSensor} appropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link WindSpeedSensor} appopriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see WindSpeedSensor
     */
    public final WindSpeedSensor getWindSpeedSensor() {
        return this.windSpeedSensor;
    }

    /**
     * A getter method for the {@link WindVaneSensor} appropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link WindSpeedSensor} appropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     * @see WindVaneSensor
     */
    public final WindVaneSensor getWindVaneSensor() {
        return this.windVaneSensor;
    }

    /**
     * A getter method for the {@link AirQualitySensor} appropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link AirQualitySensor} appropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see AirQualitySensor
     */
    public final AirQualitySensor getAirQualitySensor() {
        return this.airQualitySensor;
    }

}


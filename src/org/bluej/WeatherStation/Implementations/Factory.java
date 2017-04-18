package org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Sensors.*;
import org.bluej.WeatherStation.MockSensors.*;


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
                break;

            default:
                throw new UnsupportedOperationException(
                    "The provided platform is not supported"
                );
        }
    }

    /**
     * A getter method for the {@link HumiditySensor} apropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link HumiditySensor} apropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see HumiditySensor
     */
    public final HumiditySensor getHumiditySensor() {
        return this.humiditySensor;
    }

    /**
     * A getter method for the {@link PressureSensor} apropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link PressureSensor} apropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see PressureSensor
     */
    public final PressureSensor getPressureSensor() {
        return this.pressureSensor;
    }

    /**
     * A getter method for the {@link TemperatureSensor} apropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link TemperatureSensor} apropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see TemperatureSensor
     */
    public final TemperatureSensor getAmbientTemperatureSensor() {
        return this.ambientTemperatureSensor;
    }

    /**
     * A getter method for the {@link windSpeedSensor} apropriate for the
     * {@link Platform} the {@link Factory} instance was constructed with.
     *
     * @return The {@link WindSpeedSensor} apropriate for the {@link Platform}
     * the {@link Factory} instance was constructed with.
     *
     * @see WindSpeedSensor
     */
    public final WindSpeedSensor getWindSpeedSensor() {
        return this.windSpeedSensor;
    }

}


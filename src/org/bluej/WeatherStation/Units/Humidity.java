package org.bluej.WeatherStation.Units;

import org.bluej.WeatherStation.Sensors.HumiditySensor;

/**
 * A class for representing a humidity value to be returned by a {@link
 * HumiditySensor}.
 *
 * @author  Joe Reid
 * @see HumiditySensor
 */
public class Humidity {
    /** A constant used to convert decimal values to percentage values.*/
    public static final double PERCENTAGE_COEFFICIENT = 100;

    /** humidity as a fractional value.*/
    private final double rawHumidity;

    /**
     * The sole constructor for a {@link Humidity} object.
     *
     * @param sensorHumidity the humidity value between 0 and 1
     */
    public Humidity(final double sensorHumidity) {
        this.rawHumidity = sensorHumidity;
    }

    /**
     * A method for getting the internal rawHumidity value as represented in a
     * percentage value.
     *
     * @return The humidity value in percent
     * @see HumiditySensor
     */
    public final double inPercent() {
        return this.rawHumidity * Humidity.PERCENTAGE_COEFFICIENT;
    }

    /**
     * A method for getting a string representation of the internal rawHumidity
     * value as represented in a percentage value.
     *
     * @return The humidity value in percent followed by a percent symbol
     * @see HumiditySensor
     */
    public final String toString() {
        return Double.toString(this.inPercent()) + "%";
    }
}

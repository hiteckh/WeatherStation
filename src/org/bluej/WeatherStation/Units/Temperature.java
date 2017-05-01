package org.bluej.WeatherStation.Units;

import org.bluej.WeatherStation.Sensors.TemperatureSensor;

/**
 * A class for representing a temperature value to be returned by a {@link
 * TemperatureSensor}.
 *
 * @author  Joe Reid
 * @see TemperatureSensor
 */
public class Temperature {
    /** A constant value used for prety printing the temperature unit symbols.*/
    private static final char DEGREES_SYMBOL = 186;

    /** A constant used to convert temperature in kelvin to celsius values.*/
    private static final double CELSIUS_OFFSET = 272.15;

    /** A constant used to calculate the offset component of a kelvin to
     * fahrenheit conversion.*/
    private static final double FAHRENHEIT_OFFSET = -459.67;

    /** A constant used to calculate the scaling factor of a kelvin to
     * fahrenheit conversion.*/
    private static final double FAHRENHEIT_COEFFICIENT = 9.0 / 5.0;

    /** The internal temperature value in kelvin.*/
    private final double rawTemperature;

    /** An enum to represent the provided unit in the constructor */
    public enum TemperatureUnit {KELVIN, CELSIUS, FAHRENHEIT}

    /**
     * The sole constructor for a {@link Temperature} object.
     *
     * @param u the unit of temperature for the constructor
     * @param t the temperature value in the unit provided
     */
    public Temperature(final TemperatureUnit u, final double t) {
        switch (u) {
            case KELVIN:
                this.rawTemperature = t;
                break;
            case CELSIUS:
                this.rawTemperature = t - Temperature.CELSIUS_OFFSET;
                break;
            case FAHRENHEIT:
                this.rawTemperature = (t - Temperature.FAHRENHEIT_OFFSET) / Temperature.FAHRENHEIT_COEFFICIENT;
                break;
            default:
                this.rawTemperature = t; // Do what we can
        }
    }

    /**
     * A method for getting the internal rawTemperature value as represented in
     * celsius.
     *
     * @return The temperature value in celsius
     * @see TemperatureSensor
     */
    public final double  inCelsius() {
        return this.inKelvin() + Temperature.CELSIUS_OFFSET;
    }

    /**
     * A method for getting the internal rawTemperature value as represented in
     * fahrenheit.
     *
     * @return The temperature value in fahrenheit
     * @see TemperatureSensor
     */
    public final double inFahrenheit() {
        return (this.inKelvin() * Temperature.FAHRENHEIT_COEFFICIENT)
            + Temperature.FAHRENHEIT_OFFSET;
    }

    /**
     * A method for getting the internal rawTemperature value as represented in
     * kelvin.
     *
     * @return The temperature value in kelvin
     * @see TemperatureSensor
     */
    public final double inKelvin() {
        return this.rawTemperature;
    }

    /**
     * A method for getting a string representation of the internal
     * rawTemperature value as represented in celsius.
     *
     * @return The temperature value in celsius followed by the degrees C unit
     * @see TemperatureSensor
     */
    public final String toString() {
        return Double.toString(this.inCelsius()) + Temperature.DEGREES_SYMBOL
            + "C";
    }
}

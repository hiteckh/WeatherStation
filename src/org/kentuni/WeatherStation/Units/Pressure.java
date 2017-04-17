package org.kentuni.WeatherStation.Units;

/**
 * A class for representing a pressure value to be returned by a {@link
 * PressureSensor}.
 *
 * @author  Joe Reid
 * @see PressureSensor
 */
public class Pressure {
    /** A constant used to convert atmosphere pressure values to millibar
     * values.*/
    private static final double MILLIBAR_COEFFICIENT = 1013.2501;

    /** A constant used to convert atmosphere pressure values to inches of
     * mercury values.*/
    private static final double INCHES_OF_MERCURY_COEFFICIENT = 29.9212583001;

    /** The internal pressure value in atmospheres.*/
    private final double rawPressure;

    /**
     * The sole constructor for a {@link Pressure} object.
     *
     * @param pascals the pressure value in pascals
     */
    public Pressure(final double pascals) {
        this.rawPressure = (pascals/100)/Pressure.MILLIBAR_COEFFICIENT;
    }

    /**
     * A method for getting the internal rawPressure value as represented in
     * atmospheres.
     *
     * @return The pressure value in atmospheres
     * @see PressureSensor
     */
    public final double inAtmospheres() {
        return this.rawPressure;
    }

    /**
     * A method for getting the internal rawPressure value as represented in
     * millibars.
     *
     * @return The pressure value in millibars.
     * @see PressureSensor
     */
    public final double inMillibars() {
        return this.rawPressure * Pressure.MILLIBAR_COEFFICIENT;
    }

    /**
     * A method for getting the internal rawPressure value as represented in
     * inches of mercury.
     *
     * @return The pressure value in inches of mercury.
     * @see PressureSensor
     */
    public final double inInchesOfMercury() {
        return this.rawPressure * Pressure.INCHES_OF_MERCURY_COEFFICIENT;
    }

    /**
     * A string representation of the internal rawPressure value as represented
     * in millibars atmospheres.
     *
     * @return The pressure value in atmospheres followed by the atm unit.
     * @see PressureSensor
     */
    public final String toString() {
        return Double.toString(this.inAtmospheres()) + "atm";
    }
}

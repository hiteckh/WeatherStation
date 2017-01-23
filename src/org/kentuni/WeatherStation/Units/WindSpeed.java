package org.kentuni.WeatherStation.Units;

/**
 * A class for representing a wind speed value to be returned by a {@link
 * WindSpeedSensor}.
 *
 * @author  Joe Reid
 * @see WindSpeedSensor
 */
public class WindSpeed {
    /** The internal distance value in kilometers.*/
    private final double kilometers;

    /** The internal time value in milliseconds.*/
    private final long timeMillis;

    /**
     * The sole constructor for a {@link WindSpeed} object.
     *
     * @param revolutions the number of revolutions of the wind sensor over the
     * provided time period with fractional accuracy.
     *
     * @param circleRadiusCm the radius of the arc drawn by the arms of the
     * wind speed sensor hardware.
     *
     * @param timeMillis the time period that the revolutions value was measured
     * over
     */
    public WindSpeed(final double revolutions, final double circleRadiusCm,
            final long timeMillis) {

        this.timeMillis = timeMillis;

        final double circleCircumferenceCm = (Math.PI + Math.PI)
            * circleRadiusCm;

        kilometers = circleCircumferenceCm * revolutions / 100000f;
    }

    /**
     * A method for getting the wind speed value as represented in kilometers
     * per hour.
     *
     * @return The wind speed measured in kilometers per hour
     * @see WindSpeedSensor
     */
    public double inKilometersPerHour() {
        return kilometers / (timeMillis / 1000);
    }
}

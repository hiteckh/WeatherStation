 /**
 * A class for representing a wind speed value to be returned by a {@link
 * WindSpeedSensor}.
 *
 * @author  Joe Reid
 * @see WindSpeedSensor
 */
public class WindSpeed {
    /**
     * The radius of the anemometer, in cm.
     */
    public static final double CIRCLE_RADIUS_CM = 9;

    /**
     * The amount to multiply our results by to correct for lost energy spinning the anemometer.
     */
    public static final double CALIBRATION_FACTOR = 1.18;
    
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
    public WindSpeed(final int halfRevolutions,
            final long timeMillis) {

        this.timeMillis = timeMillis;
        
        double revolutions = halfRevolutions / 2f;

        // 2πr
        final double circleCircumferenceCm = 2 * Math.PI * CIRCLE_RADIUS_CM;

        kilometers = CALIBRATION_FACTOR * circleCircumferenceCm * revolutions / 100000f;
    }

    /**
     * A method for getting the wind speed value as represented in kilometers
     * per hour.
     *
     * @return The wind speed measured in kilometers per hour
     * @see WindSpeedSensor
     */
    public double inKilometersPerHour() {
        return kilometers / (timeMillis / (1000 * 60 * 60));
    }
}
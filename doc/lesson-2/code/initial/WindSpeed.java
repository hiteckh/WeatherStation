 /**
 * A class for representing a wind speed value to be returned by a {@link
 * WindSpeedSensor}.
 *
 * @author  Joe Reid
 * @see WindSpeedSensor
 */
public class WindSpeed {

    private int halfRevolutions;
    
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
        this.halfRevolutions = halfRevolutions;
    }

    /**
     * A method for getting the wind speed value as represented in kilometers
     * per hour.
     *
     * @return The wind speed measured in kilometers per hour
     * @see WindSpeedSensor
     */
    public double inKilometersPerHour() {
        return halfRevolutions;
    }
}
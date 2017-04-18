package org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Drivers.Anemometer;
import org.bluej.WeatherStation.Drivers.AnemometerListener;
import org.bluej.WeatherStation.Units.WindSpeed;
import org.bluej.WeatherStation.Sensors.WindSpeedSensor;


public class PiWindSpeedSensor implements WindSpeedSensor, AnemometerListener {

    /**
     * The radius of the anemometer, in cm.
     */
    private static double CIRCLE_RADIUS_CM = 9;

    /**
     * The amount to multiply our results by to correct for lost energy spinning the anemometer.
     */
    private static double CALIBRATION_FACTOR = 1.18;

    /**
     * The number of times the anemometer has turned a half-circle.
     */
    private int count = 0;

    /**
     * The last time something asked for the wind speed.
     */
    private long lastCollectionTimeMillis;

    public PiWindSpeedSensor() {
        // Asks the anemometer to let us know when it's completed half a turn.
        Anemometer.getInstance().addListener(this);

        // When the object is first created, the last collection time is set to the current time.
        resetCounter();
    }

    /**
     * Resets the counter, and the last collection time.
     */
    private void resetCounter() {
        count = 0;
        lastCollectionTimeMillis = System.currentTimeMillis();
    }

    /**
     * Gets the wind speed as the number of times the anemometer has completed a full circle since the last time
     * something asked for the wind speed.
     * {@link WindSpeed} handles most of the maths.
     * @return The wind speed.
     */
    public WindSpeed getWindSpeed() {
        // Asks the Raspberry Pi what time it is, in milliseconds.
        final long currentTimeMillis = System.currentTimeMillis();

        // The amount of time that's passed since something last asked for the wind speed, in milliseconds.
        final long timePassedMillis = currentTimeMillis - lastCollectionTimeMillis;

        // Creates a new WindSpeed object, letting it know how many rotations have occurred, the circle radius,
        // and how long we've been counting, in milliseconds.
        WindSpeed rtn = new WindSpeed(count / 2, CIRCLE_RADIUS_CM, CALIBRATION_FACTOR, timePassedMillis);
        resetCounter();

        return rtn;
    }

    /**
     * Called when the anemometer completes half a turn.
     * We keep count by incrementing {@link #count}.
     */
    @Override
    public void onTriggered() {
        count++;
    }
}

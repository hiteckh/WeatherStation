package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Drivers.Anemometer;
import org.kentuni.WeatherStation.Drivers.AnemometerListener;
import org.kentuni.WeatherStation.Units.WindSpeed;
import org.kentuni.WeatherStation.Sensors.WindSpeedSensor;


class PiWindSpeedSensor implements WindSpeedSensor, AnemometerListener {

    /**
     * The radius of the anemometer, in cm.
     */
    private static double CIRCLE_RADIUS_CM = 9;

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

        resetCounter();

        // Creates a new WindSpeed object, letting it know how many rotations have occurred, the circle radius,
        // and how long we've been counting, in milliseconds.
        return new WindSpeed(count / 2, CIRCLE_RADIUS_CM, timePassedMillis);
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

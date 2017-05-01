package org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.WindSpeedSensor;
import org.bluej.WeatherStation.Units.WindSpeed;
import java.util.Random;

/**
 * Mock implementation of the wind speed sensor.
 */
public class MockWindSpeedSensor implements WindSpeedSensor {

    /**
     * The default circle radius, in ms.
     */
    private static final double DEFAULT_CIRCLE_RADIUS = 1 / (2 * Math.PI);

    /**
     * The default amount of time that's passed, in ms.
     * @see MockWindSpeedSensor#MockWindSpeedSensor()
     */
    private static final int DEFAULT_TIME_MILLIS = 1000;

    /**
     * The minimum number of revolutions randomly generated.
     */
    private static final int MIN_REVOLUTIONS = 10000000;


    /**
     * The maximum number of revolutions randomly generated.
     */
    private static final int MAX_REVOLUTIONS = 100000000;
    /**
     * The number of revolutions.
     */
    private double revolutions;

    /**
     * The radius of the circle, in cm.
     */
    private double circleRadiusCm;

    /**
     * The time, in ms.
     */
    private long timeMillis;

    /**
     * Constructor.
     * Randomly generated revolutions between {@value MIN_REVOLUTIONS} and {@value MAX_REVOLUTIONS}.
     */
    public MockWindSpeedSensor() {
        Random rnd = new Random();

        this.revolutions = rnd.nextDouble() * (double) (MAX_REVOLUTIONS - MIN_REVOLUTIONS) + MIN_REVOLUTIONS;
        this.circleRadiusCm = DEFAULT_CIRCLE_RADIUS;
        this.timeMillis = DEFAULT_TIME_MILLIS;
    }

    /**
     * Constructor.
     * Uses the given values to set the wind speed.
     * @param revolutions Number of revolutions.
     * @param circleRadiusCm Radius of the circle, in cm.
     * @param timeMillis The time that's passed, in ms.
     */
    public MockWindSpeedSensor(final double revolutions, final double circleRadiusCm, final long timeMillis) {
        this.revolutions = revolutions;
        this.circleRadiusCm = circleRadiusCm;
        this.timeMillis = timeMillis;
    }

    /**
     * {@inheritDoc}
     */
    public WindSpeed getWindSpeed() {
        return new WindSpeed(this.revolutions, this.circleRadiusCm, 1, this.timeMillis);
    }

}

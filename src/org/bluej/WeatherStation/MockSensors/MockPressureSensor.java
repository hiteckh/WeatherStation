package src.org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.PressureSensor;
import org.bluej.WeatherStation.Units.Pressure;

import java.util.Random;

/**
 * Mock implementation of the pressure sensor.
 * Generates a single random pressure, and reuses it.
 */
public class MockPressureSensor implements PressureSensor {

    /**
     * The minimum pressure value, in atmospheres.
     */
    private static final double MIN_ATMOSPHERES = 0.9;

    /**
     * The maximum pressure value, in atmospheres.
     */
    private static final double MAX_ATMOSPHERES = 1.0;

    /**
     * The pressure, in atmospheres.
     */
    private double pressureAtmospheres;

    /**
     * Constructor.
     * Generates a pressure value between {@value MIN_ATMOSPHERES} and {@value MAX_ATMOSPHERES}.
     */
    public MockPressureSensor() {
        Random rnd = new Random();
        this.pressureAtmospheres = rnd.nextDouble() * (MAX_ATMOSPHERES - MIN_ATMOSPHERES) + MIN_ATMOSPHERES;
    }

    /**
     * Constructor.
     * Pressure set to the given value.
     * @param atmospheres Value to set the pressure to.
     */
    public MockPressureSensor(final float atmospheres) {
        this.pressureAtmospheres = atmospheres;
    }

    /**
     * {@inheritDoc}
     */
    public Pressure getPressure() {
        return new Pressure(this.pressureAtmospheres);
    }

}

package src.org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.TemperatureSensor;
import org.bluej.WeatherStation.Units.Temperature;
import java.util.Random;

/**
 * Mock implementation of the temperature sensor.
 * Generates a single random temperature and reuses it.
 */
public class MockTemperatureSensor implements TemperatureSensor {

    /**
     * Min value for the temperature.
     */
    private static final double MIN_VALUE = 253;

    /**
     * Max value for the temperature.
     */
    private static final double MAX_VALUE = 320;

    /**
     * The temperature, in kelvin.
     */
    private double rawTemperature;

    /**
     * Constructor.
     * Generates a uniformly distributed temperature between {@value MIN_VALUE} and {@value MAX_VALUE}.
     */
    public MockTemperatureSensor() {
        Random rnd = new Random();

        this.rawTemperature = rnd.nextDouble() * (MAX_VALUE - MIN_VALUE) + MIN_VALUE;
    }

    /**
     * Constructor.
     * Sets the temperature to the given value.
     * @param temperature The temperature to use, in kelvin.
     */
    public MockTemperatureSensor(final float temperature) {
        this.rawTemperature = temperature;
    }

    /**
     * {@inheritDoc}
     */
    public Temperature getTemperature() {
        return new Temperature(Temperature.TemperatureUnit.KELVIN, this.rawTemperature);
    }

}

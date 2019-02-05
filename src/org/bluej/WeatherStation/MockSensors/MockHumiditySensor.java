package src.org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.HumiditySensor;
import org.bluej.WeatherStation.Units.Humidity;
import java.util.Random;

/**
 * Mock implementation of the humidity sensor.
 * Generates a random humidity once, and then reuses it.
 */
public class MockHumiditySensor implements HumiditySensor {

    /**
     * Used to balance humidity percentage towards lower values.
     */
    private static final double HUMIDITY_MULTIPLIER = 0.8;

    /**
     * The minimum allowed humidity.
     */
    private static final double MIN_HUMIDITY = 0.2;

    /**
     * Store the humidity as a raw percentage.
     */
    private double rawHumidity;

    /**
     * Creates a value between {@value MIN_HUMIDITY} and {@value HUMIDITY_MULTIPLIER}.
     */
    public MockHumiditySensor() {
        final Random rnd = new Random();

        double humidity = rnd.nextDouble() * HUMIDITY_MULTIPLIER;

        while (humidity < MIN_HUMIDITY) {
            humidity = rnd.nextDouble() * HUMIDITY_MULTIPLIER;
        }

        this.rawHumidity = humidity;
    }

    /**
     * Given a humidity value to use.
     * @param humidity The humidity value to use.
     */
    public MockHumiditySensor(final float humidity) {
        this.rawHumidity = humidity;
    }

    /**
     * {@inheritDoc}
     */
    public Humidity getHumidity() {
        return new Humidity(this.rawHumidity);
    }

}


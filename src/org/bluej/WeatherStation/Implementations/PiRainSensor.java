package src.org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Drivers.RainMeter;
import org.bluej.WeatherStation.Drivers.RainMeterListener;
import org.bluej.WeatherStation.Sensors.RainSensor;
import org.bluej.WeatherStation.Units.RainFall;

/**
 * Implementation of the rain sensor.
 * Uses {@link RainMeter}.
 */
public class PiRainSensor implements RainSensor, RainMeterListener {

    /**
     * The amount of rainfall the rain sensor bucket manages to catch.
     */
    public static final double BUCKET_SIZE_MM = 0.2794;

    /**
     * The number of times the rain meter has collected 0.2794mm of rain.
     */
    private int count = 0;

    /**
     * The constructor.
     * Gets the RainMeter object (getInstance), and asks it to tell us when there is rain.
     */
    public PiRainSensor() {
        RainMeter rainMeter = RainMeter.getInstance();
        rainMeter.addListener(this);
    }

    /**
     * Resets the counter, and the last collection time.
     */
    public void resetCounter() {
        count = 0;
    }

    /**
     * {@inheritDoc}
     * Get the amount of rainfall since we last reset the counter.
     */
    @Override
    public RainFall getRainfall() {
        return new RainFall(count * BUCKET_SIZE_MM);
    }

    /**
     * Called when the rain meter records 0.2794mm of rainfall.
     */
    @Override
    public void onTriggered() {
        count++;
    }
}

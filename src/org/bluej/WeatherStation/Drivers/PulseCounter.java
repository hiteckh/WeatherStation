package src.org.bluej.WeatherStation.Drivers;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.PinState;

/**
 * This class is used to implement pulse counting on a GPIO input.
 * @author Jim Darby
 */
public class PulseCounter implements GpioPinListenerDigital {
    /**
     * This class is used to hold the result of a pulse count reading.
     */
    public class Result {
        /**
         * Construct a result.
         * @param count How many pulses were counted.
         * @param nanoseconds How many nanoseconds they were counted over.
         */
        public Result(final int count, final long nanoseconds)
        {
            this.count = count;
            this.nanoseconds = nanoseconds;
        }

        /**
         * Return the pulse count.
         * @return The pulse count.
         */
        public int getCount() {
            return count;
        }

        /**
         * Return the time that the pulse count was counted over.
         * @return The time in nanoseconds.
         */
        public long getNanoseconds() {
            return nanoseconds;
        }

        /** Where we store the count */
        private final int count;
        /** Where we store the nanoseconds */
        private final long nanoseconds;
    }

    /**
     * Construct a PulseCounter. This counts the HIGH to LOW transitions of the
     * input on a specific pin and with a specific debounce time.
     * @param gpio The pi4j GPIO controller to use.
     * @param pin The pin we want to count pulses on.
     * @param debounce The debounce time in milliseconds.
     */
    public PulseCounter(final GpioController gpio, final Pin pin, final int debounce)
    {
        // Locate and provision the pin.
        in = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_UP);

        // Take note of its initial state.
        lastState = in.getState();

        // Set debounce time
        in.setDebounce(debounce);

        // Initialise readings
        count = 0;
        readAt = System.nanoTime();

        // And use ourselves as the listener
        in.addListener(this);
    }

    /**
     * Stop listening and generally shut down.
     */
    public void close()
    {
        in.removeListener(this);
    }

    /**
     * Override finalize to make sure we're shut down.
     * @throws Throwable If it all goes pear shaped.
     */
    @Override
    protected void finalize() throws Throwable
    {
        close();
        super.finalize();
    }

    /**
     * Handle a pin change interrupt. Note that this is synchronized to avoid
     * multiple access the to the count and readAt object variables.
     * @param e The event we're looking at.
     */
    @Override
    public synchronized void handleGpioPinDigitalStateChangeEvent(final GpioPinDigitalStateChangeEvent e)
    {
        // What has happened?
        switch (e.getState()) {
            case HIGH:
                // We should come to HIGH from LOW.
                if (lastState != PinState.LOW) {
                    // Add 1 to the count as we (presumably) missed a LOW.
                    count += 1;
                }

                // Make a note that we're now in the HIGH state.
                lastState = PinState.HIGH;

                break;

            case LOW:
                // Increment the pulse count.
                count += 1;

                // Make a note that we're now in the LOW state.
                lastState = PinState.LOW;

                break;

            default:
                // How did we get here?
                break;
        }
    }

    /**
     * Obtain the result of the pulse counting. Note that this is a synchronised
     * method to avoid multiple access to the count and readAt object variables.
     * @return The result.
     */
    public synchronized Result getResult() {
        final long now = System.nanoTime();

        final Result r = new Result(count, now - readAt);
        count = 0;
        readAt = now;

        return r;
    }

    /** The Pin we're working with. */
    private final GpioPinDigitalInput in;
    /** The counts since the last read. */
    private int count;
    /** When we last read the data. */
    private long readAt;
    /** The pin state we last saw. */
    private PinState lastState;
}

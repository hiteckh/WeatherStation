package src.org.bluej.WeatherStation.Sensors;

/**
 * Thrown when we're unable to read reliable from a sensor,
 * either because of a hardware error, or an underlying problem in the library.
 */
public class SensorException extends RuntimeException {

    /**
     * @param e The cause of the error.
     */
    public SensorException(final Throwable e) {
        super(e);
    }

    /**
     * @param message An explanation of the error.
     * @param e The cause of the error.
     */
    public SensorException(final String message, final Throwable e) {
        super(message, e);
    }
}

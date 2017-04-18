package org.bluej.WeatherStation.Sensors;

public class SensorError extends Exception {
    public Exception cause;

    public SensorError(Exception e) {
        this.cause = e;
    }
}

package org.kentuni.WeatherStation.Sensors;

public class SensorError extends Exception {
    public Exception cause;

    public SensorError(Exception e) {
        this.cause = e;
    }
}

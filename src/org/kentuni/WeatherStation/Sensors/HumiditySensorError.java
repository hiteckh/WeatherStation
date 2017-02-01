package org.kentuni.WeatherStation.Sensors;

public class HumiditySensorError extends Exception {
    public Exception cause;

    public HumiditySensorError(Exception e) {
        this.cause = e;
    }
}

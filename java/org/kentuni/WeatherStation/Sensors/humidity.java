package org.kentuni.WeatherStation.Sensors;

public class humidity {
    // humidity as a fractional value
    private final double rawHumidity;

    public humidity(double rawHumidity) {
        this.rawHumidity = rawHumidity;
    }

    public double inPercent() {
        return this.rawHumidity * 100;
    }

    public String toString() {
        return Double.toString(this.inPercent()) + "%";
    }
}

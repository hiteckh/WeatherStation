package org.kentuni.WeatherStation.Units;

public class Humidity {
    // humidity as a fractional value
    private final double rawHumidity;

    public Humidity(double rawHumidity) {
        this.rawHumidity = rawHumidity;
    }

    public double inPercent() {
        return this.rawHumidity * 100;
    }

    public String toString() {
        return Double.toString(this.inPercent()) + "%";
    }
}

package org.kentuni.WeatherStation.Sensors;

public class Temperature {
    private static final double celsiusOffset = 272.15;
    private static final double fahrenheitOffset = -459.67;
    private static final double fahrenheitCoefficient = 9.0/5.0;
    private final double rawTemperature;

    public Temperature(double kelvin) {
        this.rawTemperature = kelvin;
    }

    public double  inCelsius() {
        return this.inKelvin() + this.celsiusOffset;
    }

    public double inFahrenheit() {
        return (this.inKelvin()*this.fahrenheitCoefficient)
            + this.fahrenheitOffset;
    }

    public double inKelvin() {
        return this.rawTemperature;
    }

    public String toString() {
        return Double.toString(this.inCelsius()) + (char)186 + "C";
    }
}

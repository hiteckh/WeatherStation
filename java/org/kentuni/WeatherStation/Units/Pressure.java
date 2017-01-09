package org.kentuni.WeatherStation.Units;

public class Pressure {
    private static final double millibarCoefficient = 1013.2501;
    private static final double inchesOfMercuryCoefficient = 29.9212583001;
    private final double rawPressure;

    public Pressure(double atmospheres) {
        this.rawPressure = atmospheres;
    }

    public double inAtomospheres() {
        return this.rawPressure;
    }

    public double inMillibars() {
        return this.rawPressure * this.millibarCoefficient;
    }

    public double inInchesOfMercury() {
        return this.rawPressure * this.inchesOfMercuryCoefficient;
    }

    public String toString() {
        return Double.toString(this.inAtomospheres()) + "atm";
    }
}

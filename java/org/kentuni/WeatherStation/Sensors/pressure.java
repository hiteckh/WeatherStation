package org.kentuni.WeatherStation.Sensors;

public class pressure {
    private static final double millibarCoefficient = 1013.2501;
    private static final double inchesOfMercuryCoefficient = 29.9212583001;
    private final double rawPressure;

    public pressure(double atmospheres) {
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

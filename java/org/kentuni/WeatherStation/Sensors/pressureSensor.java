package org.kentuni.WeatherStation.Sensors;

public interface pressure {
    public float inAtomospheres();
    public float inMillibars();
    public float inInchesOfMercury();
    public String toString();
}

public interface pressureSensor {
    public pressure getPressure();
}

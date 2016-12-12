package org.kentuni.WeatherStation.Sensors;

public interface humudity {
    public float inPercent();
    public String toString();
}

public interface humuditySensor {
    public humudity getHumudity();
}

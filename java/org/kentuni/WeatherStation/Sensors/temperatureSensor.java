package org.kentuni.WeatherStation.Sensors;

public interface temperature {
    public float inCelsius();
    public float inFahrenheit();
    public float inKelvin();
    public String toString();
}

public interface temperatureSensor {
    public temperature getTemperature();
}


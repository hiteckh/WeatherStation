package org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.TemperatureSensor;
import org.bluej.WeatherStation.Units.Temperature;
import java.util.Random;

public class MockTemperatureSensor implements TemperatureSensor {
    private double rawTemperature;

    public MockTemperatureSensor() {
        Random rnd = new Random();

        double temperature = rnd.nextDouble()*320;
        while (temperature < 253) {
            temperature = rnd.nextDouble()*320;
        }

        this.rawTemperature = temperature;
    }

    public MockTemperatureSensor(float temperature) {
        this.rawTemperature = temperature;
    }

    public Temperature getTemperature() {
        return new Temperature(Temperature.TemperatureUnit.KELVIN, this.rawTemperature);
    }

}

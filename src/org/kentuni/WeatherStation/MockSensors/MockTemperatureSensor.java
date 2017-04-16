package org.kentuni.WeatherStation.MockSensors;

import org.kentuni.WeatherStation.Sensors.TemperatureSensor;
import org.kentuni.WeatherStation.Units.Temperature;
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

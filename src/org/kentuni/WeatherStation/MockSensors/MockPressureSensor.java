package org.kentuni.WeatherStation.MockSensors;

import org.kentuni.WeatherStation.Sensors.PressureSensor;
import org.kentuni.WeatherStation.Units.Pressure;

import java.util.Random;

public class MockPressureSensor implements PressureSensor {
    private double rawPressure;

    public MockPressureSensor() {
        Random rnd = new Random();

        double at = rnd.nextDouble();
        while (at < 0.9) {
            at = rnd.nextDouble();
        }

        this.rawPressure = at;
    }

    public MockPressureSensor(float atmospheres) {
        this.rawPressure = atmospheres;
    }

    public Pressure getPressure() {
        return new Pressure(this.rawPressure);
    }

}

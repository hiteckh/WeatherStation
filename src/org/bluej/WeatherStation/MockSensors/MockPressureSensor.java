package org.bluej.WeatherStation.MockSensors;

import org.bluej.WeatherStation.Sensors.PressureSensor;
import org.bluej.WeatherStation.Units.Pressure;

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

package org.kentuni.WeatherStation.MockSensors;

import org.kentuni.WeatherStation.Sensors.WindSpeedSensor;
import org.kentuni.WeatherStation.Units.WindSpeed;
import java.util.Random;

public class MockWindSpeedSensor implements WindSpeedSensor {
    private double revolutions;
    private double circleRadiusCm;
    private long timeMillis;

    public MockWindSpeedSensor() {
        Random rnd = new Random();

        double rev = rnd.nextDouble()*10000000;
        while (rev > 10000000) {
            rev = rnd.nextDouble()*10000000;
        }

        this.revolutions = rev;
        this.circleRadiusCm = 1 / (2 * Math.PI);
        this.timeMillis = 1000;
    }

    public MockWindSpeedSensor(final double revolutions, final double circleRadiusCm, final long timeMillis) {
        this.revolutions = revolutions;
        this.circleRadiusCm = circleRadiusCm;
        this.timeMillis = timeMillis;
    }

    public WindSpeed getWindSpeed() {
        return new WindSpeed(this.revolutions, this.circleRadiusCm, this.timeMillis);
    }

}

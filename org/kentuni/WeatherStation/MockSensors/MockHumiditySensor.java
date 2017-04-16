package org.kentuni.WeatherStation.MockSensors;

import org.kentuni.WeatherStation.Sensors.HumiditySensor;
import org.kentuni.WeatherStation.Units.Humidity;
import java.util.Random;

public class MockHumiditySensor implements HumiditySensor {
    private double rawHumidity;

    public MockHumiditySensor() {
        Random rnd = new Random();

        double humidity = rnd.nextDouble()*0.8;

        while (humidity < 0.2) {
            humidity = rnd.nextDouble()*0.8;
        }

        this.rawHumidity = humidity;
    }

    public MockHumiditySensor(float humidity) {
        this.rawHumidity = humidity;
    }

    public Humidity getHumidity() {
        return new Humidity(this.rawHumidity);
    }

}


package org.kentuni.WeatherStation.Implementations;

import org.kentuni.WeatherStation.Sensors.*;
import org.kentuni.WeatherStation.MockSensors.*;

public class Factory {
    private HumiditySensor humiditySensor;
    private PressureSensor pressureSensor;
    private TemperatureSensor temperatureSensor;
    private WindSpeedSensor windSpeedSensor;

    public Factory(Platform platform) throws UnsupportedOperationException {
        switch (platform) {
            case WEATHERSTATION_V1:
                try {
                    this.humiditySensor = new PiHumiditySensor();
                    this.pressureSensor = new PiPressureSensor();
                    this.temperatureSensor = new PiTemperatureSensor();
                    this.windSpeedSensor = new PiWindSpeedSensor();
                } catch (Exception e) {
                    throw new UnsupportedOperationException("The provided platform is supported but failed to initialize");
                }
                break;

            case MOCK:
                this.humiditySensor = new MockHumiditySensor();
                this.pressureSensor = new MockPressureSensor();
                this.temperatureSensor = new MockTemperatureSensor();
                this.windSpeedSensor = new MockWindSpeedSensor();

            default:
                throw new UnsupportedOperationException("The provided platform is not supported");
        }
    }

    public HumiditySensor getHumiditySensor() {
        return this.humiditySensor;
    }

    public PressureSensor getPressureSensor() {
        return this.pressureSensor;
    }

    public TemperatureSensor getTemperatureSensor() {
        return this.temperatureSensor;
    }

    public WindSpeedSensor getWindSpeedSensor() {
        return this.windSpeedSensor;
    }

}


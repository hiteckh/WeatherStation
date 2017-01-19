package org.kentuni.WeatherStation;

import org.kentuni.WeatherStation.Sensors.*;
import org.kentuni.WeatherStation.Implementations.Factory;
import org.kentuni.WeatherStation.Implementations.Platform;

public class SimpleWeatherStation {
    private HumiditySensor humiditySensor;
    private PressureSensor pressureSensor;
    private TemperatureSensor temperatureSensor;
    private WindSpeedSensor windSpeedSensor;

    public SimpleWeatherStation() {
        // set up a sensor factory for this platform
        Factory wsFactory = new Factory(Platform.WEATHERSTATION_V1);

        // populate the internal sensor variables
        this.humiditySensor = wsFactory.getHumiditySensor();
        this.pressureSensor = wsFactory.getPressureSensor();
        this.temperatureSensor = wsFactory.getTemperatureSensor();
        this.windSpeedSensor = wsFactory.getWindSpeedSensor();
    }

    public double getPercentHumidity() {
        return this.humiditySensor.getHumidity().inPercent();
    }

    public double getMillibarPressure() {
        return this.pressureSensor.getPressure().inMillibars();
    }

    public double getCelsiusTemperature() {
        return this.temperatureSensor.getTemperature().inCelsius();
    }

    public double getKilometersPerHourWindSpeed() {
        return this.windSpeedSensor.getWindSpeed().inKilometersPerHour();
    }
}


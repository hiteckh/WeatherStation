package org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Drivers.MCP3427;
import org.bluej.WeatherStation.Sensors.AirQualitySensor;
import org.bluej.WeatherStation.Units.AirQuality;

import java.io.IOException;

public class PiAirQualitySensor implements AirQualitySensor {

	private static final int BUS_NUM = 1;
	private static final int ADDRESS = 0x6A;

	private static MCP3427 DRIVER = null;

	private static synchronized MCP3427 getDriver() throws IOException {
		if (DRIVER == null)
			DRIVER = new MCP3427(BUS_NUM, ADDRESS);
		return DRIVER;
	}

	private final MCP3427 driver;

	public PiAirQualitySensor() throws IOException {
		driver = getDriver();
	}

	@Override
	public AirQuality getAirQuality() {
		final int adc;
		try {
			adc = driver.read();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		final AirQuality direction = new AirQuality(adc);
		return direction;
	}
}

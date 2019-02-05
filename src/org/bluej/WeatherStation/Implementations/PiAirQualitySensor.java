package src.org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Drivers.MCP3427;
import org.bluej.WeatherStation.Sensors.AirQualitySensor;
import org.bluej.WeatherStation.Sensors.SensorException;
import org.bluej.WeatherStation.Units.AirQuality;

import java.io.IOException;

/**
 * Implementation of the air quality sensor.
 * Uses {@link MCP3427}.
 */
public class PiAirQualitySensor implements AirQualitySensor {

	/**
	 * The number of the air quality sensor bus.
	 */
	private static final int BUS_NUM = 1;
	/**
	 * The address of the air quality sensor.
	 */
	private static final int ADDRESS = 0x6A;

	/**
	 * Singleton instance.
	 */
	private static MCP3427 driverSingleton = null;

	/**
	 * @return The singleton.
	 */
	private static synchronized MCP3427 getDriver() {
		if (driverSingleton == null) {
			try {
				driverSingleton = new MCP3427(BUS_NUM, ADDRESS);
			} catch (final IOException e) {
				throw new SensorException(e);
			}
		}
		return driverSingleton;
	}

	/**
	 * Local driver.
	 */
	private final MCP3427 driver;

	/**
	 * Default constructor.
	 */
	public PiAirQualitySensor() {
		driver = getDriver();
	}

	/**
	 * {@inheritDoc}
	 */
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

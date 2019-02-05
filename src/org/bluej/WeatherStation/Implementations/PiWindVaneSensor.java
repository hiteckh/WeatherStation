package src.org.bluej.WeatherStation.Implementations;

import org.bluej.WeatherStation.Drivers.MCP3427;
import org.bluej.WeatherStation.Sensors.SensorException;
import org.bluej.WeatherStation.Sensors.WindVaneSensor;
import org.bluej.WeatherStation.Units.WindDirection;

import java.io.IOException;
import java.util.Arrays;

/**
 * Implementation of the wind vane.
 * Uses {@link MCP3427}.
 */
public final class PiWindVaneSensor implements WindVaneSensor {

	/**
	 * The number of the air quality sensor bus.
	 */
	private static final int BUS_NUM = 1;

	/**
	 * The address of the air quality sensor.
	 */
	private static final int ADDRESS = 0x69;

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

	public static double V_IN = 3.268;
	public static int V_DIVIDER = 75000;

	/**
	 * Local instance of the driver.
	 */
	private final MCP3427 driver;

	/**
	 * Constructor.
	 */
	public PiWindVaneSensor() {
		driver = getDriver();
	}

	/**
	 * Given an ADC value return the direction.
	 * @param adc The ADC value.
	 * @return The angle.
	 */
	private static double angleFromADC(final int adc) {
		for (final Direction dir : Direction.values()) {
			if (adc >= dir.adcMin && adc <= dir.adcMax)
				return dir.angle;
		}
		throw new IllegalStateException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WindDirection getWindDirection() {
		final int adc;
		try {
			adc = driver.read();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		final double angle = angleFromADC(adc);
		final WindDirection direction = new WindDirection(angle);
		return direction;
	}

	/**
	 * Represents the different directions.
	 */
	private enum Direction {
		N(0.0, 33000),
		NNE(22.5, 6570),
		NE(45.0, 8200),
		ENE(67.5, 891),
		E(90.0, 1000),
		ESE(112.5, 688),
		SE(135.0, 2200),
		SSE(157.5, 1410),
		S(180.0, 3900),
		SSW(202.5, 3140),
		SW(225.0, 16000),
		WSW(247.5, 14120),
		W(270.0, 120000),
		WNW(292.5, 42120),
		NW(315.0, 64900),
		NNW(337.5, 21880);

		/**
		 * An ordered set of directions.
		 */
		public static final Direction[] DIRECTIONS;

		static {
			DIRECTIONS = Direction.values();
			// Sort the directions by their ADC value.
			Arrays.sort(DIRECTIONS, (a, b) -> a.adc - b.adc);
			for (int i = 1; i < DIRECTIONS.length; i++) {
				Direction lower = DIRECTIONS[i - 1];
				Direction upper = DIRECTIONS[i];

				final int halfWay = (lower.adc + upper.adc) / 2;

				lower.adcMax = halfWay;
				upper.adcMin = halfWay + 1;
			}

			DIRECTIONS[0].adcMin = 0;
			DIRECTIONS[DIRECTIONS.length - 1].adcMax = MCP3427.MAX;
		}

		/**
		 * The angle that this direction represents.
		 * Midpoint.
		 */
		private final double angle;

		/**
		 * The corresponding mid-point ADC value.
		 */
		private final int adc;

		/**
		 * Upper and lower bounds for this ADC value.
		 * Only naturally set in {@link #DIRECTIONS}.
		 */
		private int adcMax, adcMin;

		/**
		 * Constructor.
		 * @param angle {@link #angle}.
		 * @param ohms Corresponding resistance for representing this angle on the wind vane hardware.
		 */
		Direction(final double angle, final int ohms) {
			this.angle = angle;
			final double vOut = PiWindVaneSensor.V_IN * ohms / (double) (PiWindVaneSensor.V_DIVIDER + ohms);
			this.adc = (int) (vOut * MCP3427.MAX / MCP3427.VREF);
		}
	}
}

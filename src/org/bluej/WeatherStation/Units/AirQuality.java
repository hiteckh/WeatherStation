package org.bluej.WeatherStation.Units;

/**
 * Represents the air quality.
 */
public class AirQuality {

	/**
	 * The air quality, as a percentage.
	 */
	private final double percentage;

	/**
	 * Default constructor.
	 * @param percentage The air quality, as a percentage.
	 */
	public AirQuality(final double percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return The air quality, as a percentage.
	 */
	public double getPercentage() {
		return percentage;
	}
}

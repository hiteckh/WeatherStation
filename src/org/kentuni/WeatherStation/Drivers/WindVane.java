package org.kentuni.WeatherStation.Drivers;

/**
 * Todo: Implement
 *
 * Created by harry on 27/12/2016.
 */
public class WindVane {

	/**
	 * The default time to sample the wind vane for.
	 */
	private static final int DEFAULT_SAMPLE_TIME = 10;

	/**
	 * The time, in seconds, to sample the wind vane for.
	 */
	private final int sampleTime;

	private WindVane() {
		this(DEFAULT_SAMPLE_TIME);
	}

	private WindVane(final int sampleTime) {
		this.sampleTime = sampleTime;

	}


}

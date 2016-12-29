package org.kentuni.WeatherStation.Sensors;

/**
 * Created by harry on 29/12/2016.
 */
public class WindSpeed {

	private final double kilometers;
	private final long timeMillis;

	public WindSpeed(final double revolutions, final double circleRadiusCm, final long timeMillis) {
		this.timeMillis = timeMillis;

		final double circleCircumferenceCm = (2 * Math.PI) * circleRadiusCm;

		kilometers = circleCircumferenceCm * revolutions / 100000f;
	}

	public double getKilometersPerHour() {
		return kilometers / (timeMillis / 1000);
	}
}

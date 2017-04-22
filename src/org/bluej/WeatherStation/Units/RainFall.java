package org.bluej.WeatherStation.Units;

public class RainFall {

    private final double mm;

    /**
     * Constructor.
     * @param mm The amount of rainfall, in millimeters.
     */
    public RainFall(final double mm) {
        this.mm = mm;
    }

    /**
     * Gives the amount of rainfall, in mm.
     * @return The amount of rainfall, in millimeters.
     */
    public double inMM() {
        return mm;
    }
}

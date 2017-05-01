package org.bluej.WeatherStation.Units;

/**
 * Represents rainfall.
 */
public class RainFall {

    /**
     * The rainfall, in mm.
     */
    private final double mm;

    /**
     * Constructor.
     * @param mm The amount of rainfall, in millimeters.
     */
    public RainFall(final double mm) {
        this.mm = mm;
    }

    /**
     * @return The amount of rainfall, in millimeters.
     */
    public double inMM() {
        return mm;
    }
}

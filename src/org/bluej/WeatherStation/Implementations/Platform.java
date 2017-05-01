package org.bluej.WeatherStation.Implementations;

/**
 * Represents the possible suite of sensors that might be available to us.
 */
public enum Platform {
    /**
     * Use sensors available to the first version of the weather station.
     */
    WEATHERSTATION_V1,
    /**
     * Mock sensors instead.
     */
    MOCK
}

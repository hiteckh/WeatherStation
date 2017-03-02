package org.kentuni.WeatherStation.Drivers;

/**
 * A listener to the {@link RainMeter}.
 */
public interface RainMeterListener {

    /**
     * Called when the rain meter records 0.2794mm of rainfall.
     */
    void onTriggered();
}

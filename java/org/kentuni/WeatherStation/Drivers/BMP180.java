package org.kentuni.WeatherStation.Drivers;

import java.io.IOException;

/**
 * This singleton is a low level interface to the BMP180 air pressure and
 * temperature sensor.
 * 
 * @author Joe Reid.
 */
public enum BMP180 {
    INSTANCE;

    public class Reading {
        private final int temperature;
        private final int pressure;
        private final int mode;

        public Reading (int temperature, int pressure, int mode) {
            this.temperature = temperature;
            this.pressure = pressure;
            this.mode = mode;
        }

        public int getTemperature() {
            return this.temperature;
        }

        public int getPressure() {
            return this.pressure;
        }

        public int getMode() {
            return this.mode;
        }
    }

    private int mode;

    /**
     * Get the mode of the BMP180 sensor
     * 
     * @return An integer representation of the mode.
     */
    public int getMode() {
        return this.mode;
    }

    /**
     * Set the mode of the BMP180 sensor
     * 
     * @param mode The level of delay stabilisation. 0 = one sample, 1 = two
     * samples, 2 = four samples and 3 = 8 samples.
     * Invalid values will result in an {@code IOException}.
     *
     * @throws IOException In the case of invalid mode selection
     */
    public void setMode(int mode) throws IOException {
        if (mode < 0 || mode > 3) {
            throw new IOException ("BMP180: Invalid mode");
        }

        this.mode = mode;
    }

    /**
     * Read the temperature and pressure from the device.
     * 
     * @return A {@code BMP180.Reading} object containing the values read.
     */
    public Reading read () {
        return new Reading(0,0, this.mode);
    }
}

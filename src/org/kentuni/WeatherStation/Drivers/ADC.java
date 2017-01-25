package org.kentuni.WeatherStation.Drivers;

import java.io.IOException;

import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CBus;

/**
 * This class provides a singleton interface to the MCP3427 ADC class {@link
 * MCP3427}.
 *
 * @author Joe Reid.
 */
public class ADC {
    private static MCP3427 instance = null;

    public static MCP3427 getDriver() throws IOException {
        if (ADC.instance == null) {

            synchronized (ADC.class) {
                if (ADC.instance == null) {
                    ADC.instance = new MCP3427(
                        I2CFactory.getInstance (I2CBus.BUS_1, 0x69)
                    );
                }
            }
        }

        return ADC.instance;
    }
}

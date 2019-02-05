package src.org.bluej.WeatherStation.Drivers;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is used to interface to the DS18B20 temperature sensor.
 *
 * @author Jim Darby.
 */
public class DS18B20 {
    /**
     * This class is used as a trivial extension to IOException. It's purpose
     * is to more accurately control exception catching and throwing when
     * parsing the sensor results.
     */
    public static class MyException extends IOException
    {
        /**
         * Construct an exception.
         * @param why The string text to used. Passed directly to the
         * IOException constructor.
         */
        public MyException(final String why)
        {
            super(why);
        }
    }

    /**
     * This is the constructor for the {@code DS18B20} class. It takes no
     * parameters as the files and parameters are fixed.
     * @throws IOException Thrown when we can't communicate with the hardware.
     */
    public DS18B20() throws IOException
    {
        File deviceDir = new File(DIR_NAME);
        File[] files = deviceDir.listFiles();
        String file = null;

        // Scan through the files, if we found any
        if (files != null)
            for (final File f : files) {
                final String name = f.getName();

                if (name.startsWith("28-")) {
                    file = DIR_NAME + '/' + name + "/w1_slave";
                    break;
                }
            }

        if (file == null)
            throw new MyException("DS18B20 not found!");

        this.file = file;
    }

    /**
     * Is the device found?
     * @return {@code true} if the device has been detected.
     */
    public boolean ok()
    {
        return file != null;
    }

    /**
     * Return the name of the device file.
     * @return The file name.
     */
    public String getFile()
    {
        return file;
    }

    /**
     * Read the temperature from the device.
     * @return The temperature in degrees Celsius.
     * @throws MyException If there is a problem in reading the device.
     */
    public double read() throws MyException
    {
        // Check we're essentially OK before proceeding
        if (file == null)
            throw new MyException("DS18B20: No file found");

        // Now run through the device output
        int lineno = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // Iterate once per line
            for (String line; (line = reader.readLine()) != null;) {
                lineno += 1;

                // Check the lines coming from the device
                switch (lineno) {
                    // The first line includes the checksum result
                    case 1:
                        if (!line.endsWith("YES"))
                            throw new MyException("DS18B20: CRC failure");
                        break;

                    // The second line includes the data from the device
                    case 2:
                        // Find the part of interest
                        final int start = line.indexOf("t=");

                        if (start < 0)
                            throw new MyException("DS18B20: Malformed result line (no t=)");

                        // Try to parse it.
                        try {
                            final Integer i = new Integer(line.substring(start + 2));

                            return i / 1000.0;
                        }

                        // If that failed return an exception
                        catch (NumberFormatException e) {
                            throw new MyException("DS18B20: Malformed result line (number)");
                        }
                }
            }
        }

        catch (FileNotFoundException e) {
            throw new MyException("DS18B20: file " + file + " cannot be opened");
        }

        catch (IOException e) {
            throw new MyException("DS18B20: file " + file + " cannot be read");
        }

        // If we didn't get enough data then complain.
        throw new MyException("DS18B20: Unexpected EOF");
    }

    private static final String DIR_NAME = "/sys/bus/w1/devices";
    private final String file;
}

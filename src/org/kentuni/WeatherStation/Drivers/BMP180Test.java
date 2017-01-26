import org.kentuni.WeatherStation.Drivers.BMP180;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CBus;

public class BMP180Test {
    @Test
    public void testInstantiation() throws Exception {

        BMP180 testItem = new BMP180(
            I2CFactory.getInstance(I2CBus.BUS_1),
            0x77
        );

        assertNotNull("Should not be null", testItem);
    }


    @Test
    public void testReadModeOne() throws Exception {
        BMP180 testItem = new BMP180(
            I2CFactory.getInstance(I2CBus.BUS_1),
            0x77
        );

        BMP180.Result res = testItem.read(1);
        int pressure = res.getPressure();
        int temperature = res.getTemperature();

        // Pressure should not ever be over 110 kPa
        assertThat(pressure, lessThan(110000));

        // pressure should not ever be under 90kPa
        assertThat(pressure, greaterThan(90000));

        // Temperature shouldn't be less than 10 c in testing
        assertThat(temperature, greaterThan(100));

        // Temperature shouldn't be more than 35 c in testing
        assertThat(temperature, lessThan(350));
    }
}

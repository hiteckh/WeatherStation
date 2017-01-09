 

import com.pi4j.io.gpio.GpioController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.kentuni.WeatherStation.Drivers.Gpio;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(Main::demoJFreeChart);
		//final GpioController gpio = Gpio.getController();
	}

	public static void demoJFreeChart() {
		JFrame frame = new JFrame("Charts");

		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		XYDataset ds = createDataset();
		JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
				"x", "y", ds, PlotOrientation.VERTICAL, true, true,
				false);

		ChartPanel cp = new ChartPanel(chart);

		frame.getContentPane().add(cp);
	}

	private static XYDataset createDataset() {

		DefaultXYDataset ds = new DefaultXYDataset();

		double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };

		ds.addSeries("series1", data);

		return ds;
	}
}

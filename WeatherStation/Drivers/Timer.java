package org.kentuni.WeatherStation.Drivers;

import javax.swing.*;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Timer<T> {

	/**
	 * The timer to use.
	 */
	private static final java.util.Timer timer = new java.util.Timer(true);

	/**
	 * The time, in seconds, to wait between calls.
	 */
	private final int time;

	/**
	 * The listener to notify.
	 * Expected to be some form of display.
	 */
	private final Consumer<T> listener;

	/**
	 * The supplier to gather the information from.
	 * Expected to be some form of driver.
	 */
	private final Supplier<T> supplier;

	/**
	 * Constructor.
	 * @param listener The listener to receive data every {@link #time} seconds.
	 * @param supplier The supplier of the data. Usually a driver.
	 * @param time The time, in seconds, between calls.
	 */
	public Timer(final Consumer<T> listener, final Supplier<T> supplier, final int time) {

		this.listener = listener;
		this.supplier = supplier;
		this.time = time;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				call();
			}
		}, 0, time * 1000);
	}

	/**
	 * Called every {@link #time} seconds.
	 * Collects the response from the supplier on the timer thread.
	 * Gives the response to the listener on the Swing UI thread at a later date.
	 */
	private void call() {
		final T response = supplier.get();
		SwingUtilities.invokeLater(() -> listener.accept(response));
	}

	/**
	 * Stops the timer.
	 */
	public void stop() {
		timer.cancel();
	}
}

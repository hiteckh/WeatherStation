package org.bluej.WeatherStation.DatabaseUploader;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.ProtocolException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Runs on a separate thread and attempts upload sensor data to the cloud.
 */
public class Uploader extends Thread {

    /**
     * The HTTP response code we expect to receive from the Oracle Cloud.
     */
    private static final int EXPECTED_CLOUD_RESPONSE = 201;

    /**
     * The amount of time to wait between uploads, in ms.
     */
    private static final int SLEEP_TIME_MS = 3600 * 1000;

    /**
     * The config to use.
     */
    private final Config config;

    /**
     * Default constructor.
     * @param config The config to use.
     * @throws ClassNotFoundException Thrown when no MySql driver could be found.
     */
    public Uploader(final Config config) throws ClassNotFoundException {
        this.config = config;
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     * {@inheritDoc}
     * Attempts to upload sensor data.
     */
     @Override
     public void run() {
         while (true) {
             // Run an upload
             try {
                doUpload();
             } catch (CloudException e) {
                 //LOG.log (Level.SEVERE, "Error uploading to cloud: {0}", e.getMessage ());
             }

             try {
                 // Wait for an hour before trying it again
                 Thread.sleep(SLEEP_TIME_MS);
             } catch (InterruptedException e) {
                 //LOG.log (Level.SEVERE, "Thread interrupted: {0}", e.getMessage ());
             }
         }
    }

    /**
     * Attempts to move sensor data from MySQL to the Oracle Cloud.
     * @throws CloudException Thrown when either downloading or uploading the data fails.
     */
    private void doUpload() throws CloudException {
        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://" + this.config.getMysqlHost() + ":3306/" + this.config.getMysqlDB(),
                        this.config.getMysqlUser(),
                        this.config.getMysqlPass()
                )
        ) {
            try (
                    PreparedStatement command = con.prepareStatement(
                            "SELECT * FROM WEATHER_MEASUREMENT WHERE REMOTE_ID IS NULL LIMIT 50",
                            ResultSet.TYPE_FORWARD_ONLY,
                            ResultSet.CONCUR_UPDATABLE
                    );
                    ResultSet r = command.executeQuery()
            ) {
                // Loop over records
                while (r.next()) {
                    final int remote = this.sendToCloud(r);
                    if (remote > 0) {
                        r.updateInt("REMOTE_ID", remote);
                        r.updateRow();
                    }
                }
            } catch (final SQLException e) {
                throw new CloudException(e);
            }
        } catch (final SQLException e) {
            throw new CloudException(e);
        }

        // clean up connections
        sendToCloud(null);
    }

    /**
     * Sends the given result set to the Oracle Cloud.
     * @param r The result set to use.
     * @return The remote ID.
     * @throws CloudException Thrown if unable to upload to the cloud.
     */
    private int sendToCloud(final ResultSet r) throws CloudException {
        final HttpsURLConnection con; // re-uses the underlying connection

        try {
            final String url = this.config.getCloudURL();
            con = (HttpsURLConnection) new URL(url).openConnection();
        } catch (final IOException e) {
            throw new CloudException(e);
        }

        // null used to close the underlying conn
        if (r == null) {
            con.disconnect();
            return 0;
        }

        try {
            con.setRequestMethod("POST");
            con.setDoOutput(false);
            con.setDoInput(true);
            con.setRequestProperty("Content-type", "tex/plain");
            con.setRequestProperty("Accept", "text/plain");
        } catch (final ProtocolException e) {
            throw new CloudException(e);
        }

        try {
            con.setRequestProperty("LOCAL_ID", String.valueOf(r.getInt("ID")));
            con.setRequestProperty("AMB_TEMP", String.valueOf(r.getFloat("AMBIENT_TEMPERATURE")));
            con.setRequestProperty("GND_TEMP", String.valueOf(r.getFloat("GROUND_TEMPERATURE")));

            con.setRequestProperty("AIR_QUALITY", String.valueOf(r.getFloat("AIR_QUALITY")));
            con.setRequestProperty("AIR_PRESSURE", String.valueOf(r.getFloat("AIR_PRESSURE")));
            con.setRequestProperty("HUMIDITY", String.valueOf(r.getFloat("HUMIDITY")));
            con.setRequestProperty("WIND_DIRECTION", String.valueOf(r.getFloat("WIND_DIRECTION")));
            con.setRequestProperty("WIND_SPEED", String.valueOf(r.getFloat("WIND_SPEED")));
            con.setRequestProperty("WIND_GUST_SPEED", String.valueOf(r.getFloat("WIND_GUST_SPEED")));
            con.setRequestProperty("RAINFALL", String.valueOf(r.getFloat("RAINFALL")));

            final ZonedDateTime zoned = ZonedDateTime.of(
                LocalDateTime.ofEpochSecond(
                    r.getTimestamp("CREATED").getTime() / 1000,
                    0,
                    ZoneOffset.UTC
                ),
                ZoneOffset.UTC
            );

            con.setRequestProperty("READING_TIMESTAMP", zoned.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        } catch (final SQLException e) {
            throw new CloudException(e);
        }

        con.setRequestProperty("WEATHER_STN_NAME", this.config.getCloudUser());
        con.setRequestProperty("WEATHER_STN_PASS", this.config.getCloudPass());

        // By reading the response code we trigger sending the message
        try {
            final int responseCode = con.getResponseCode();
            if (responseCode != EXPECTED_CLOUD_RESPONSE) {
                throw new CloudException(new Exception("Unexpected cloud response: " + responseCode));
            }
        } catch (final IOException e) {
            throw new CloudException(e);
        }

        try (InputStream ins = con.getInputStream()) {
            final JSONObject response = new JSONObject(new JSONTokener(ins));

            try {
                final int remoteID = response.getInt("ORCL_RECORD_ID");
                while (ins.read() >= 0) {
                    // consume the rest of the data
                }

                return remoteID;
            } catch (final JSONException e) {
                throw new CloudException(e);
            }
        } catch (final IOException e) {
            throw new CloudException(e);
        }
    }

    /**
     * Thrown when unable to upload to the cloud, for whatever reason.
     */
    public class CloudException extends Exception {
        /**
         * Default constructor.
         * @param e The cause of the exception.
         */
        CloudException(final Exception e) {
            super(e);
        }
    }
}

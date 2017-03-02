package org.kentuni.WeatherStation.DatabaseUploader;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.ProtocolException;

import java.sql.Timestamp;
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

public class Uploader extends Thread {
     Config config = null;

     public Uploader(Config config) throws ClassNotFoundException {
         this.config = config;

         Class.forName ("com.mysql.jdbc.Driver");
     }

     @Override
     public void run () {
         while (true) {
             // Run an upload
             doUpload();

             try {
                 // Wait for an hour before trying it again
                 Thread.sleep (3600 * 1000);
             }

             catch (InterruptedException e) {
                 //LOG.log (Level.SEVERE, "Thread interrupted: {0}", e.getMessage ());
             }
         }
    }

    private void doUpload() throws CloudError {
        try (
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://" + this.config.mysqlHost + ":3306/" + this.config.mysqlDB,
                this.config.mysqlUser,
                this.config.mysqlPass
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
                while (r.next ()) {
                    final int remote = this.sendToCloud(r);
                    if (remote > 0) {
                        r.updateInt("REMOTE_ID", remote);
                        r.updateRow();
                    }
                }
            } catch (SQLException e) {
                throw new CloudError(e);
            }
         } catch (SQLException e) {
             throw new CloudError(e);
         }

        // clean up connections
        sendToCloud (null);
    }

    private int sendToCloud(ResultSet r) throws CloudError {
        HttpsURLConnection con; // re-uses the underlying connection

        try {
            String url = this.config.cloudURL;
            con = (HttpsURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            throw new CloudError(e);
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
        } catch (ProtocolException e) {
            throw new CloudError(e);
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
        } catch (SQLException e) {
            throw new CloudError(e);
        }

        con.setRequestProperty("WEATHER_STN_NAME", this.config.cloudUser);
        con.setRequestProperty("WEATHER_STN_PASS", this.config.cloudPass);

        // By reading the response code we trigger sending the message
        try {
            int responseCode = con.getResponseCode();
            if (responseCode != 201) {
                throw new CloudError(new Exception("Unexpected cloud response: " + responseCode));
            }
        } catch (IOException e) {
            throw new CloudError(e);
        }

        try (InputStream ins = con.getInputStream()) {
            final JSONObject response = new JSONObject(new JSONTokener(ins));

            try {
                final int remoteID = response.getInt("ORCL_RECORD_ID");
                while (ins.read() >= 0) { // consume the rest of the data
                    assert true; // nop
                }

                return remoteID;
            } catch (JSONException e) {
                throw new CloudError(e);
            }
        } catch (IOException e) {
            throw new CloudError(e);
        }
    }

    public class CloudError extends Exception {
        Exception e = null;

        public CloudError(Exception e) {
            this.e = e;
        }
    }
}

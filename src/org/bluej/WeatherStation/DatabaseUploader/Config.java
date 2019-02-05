package src.org.bluej.WeatherStation.DatabaseUploader;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Container class for all database config parameters.
 */
public final class Config {

    // MySQL config settings.
    /**
     * The host address.
     */
    private String mysqlHost = null;
    /**
     * The username.
     */
    private String mysqlUser = null;
    /**
     * The plaintext password.
     */
    private String mysqlPass = null;
    /**
     * The database to target.
     */
    private String mysqlDB   = null;

    // Oracle Cloud settings.
    /**
     * The host address.
     */
    private String cloudURL  = null;
    /**
     * The username.
     */
    private String cloudUser = null;
    /**
     * The plaintext password.
     */
    private String cloudPass = null;

    /**
     * Default constructor.
     * Loads a properties file.
     * @param filename Path of the properties file to load.
     * @throws ConfigException Thrown when unable to produce a valid config.
     */
    Config(final String filename) throws ConfigException {
        final Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(filename));
        } catch (Exception e) {
            throw new ConfigException(e);
        }

        this.mysqlHost = prop.getProperty("mysql.host");
        this.mysqlUser = prop.getProperty("mysql.user");
        this.mysqlPass = prop.getProperty("mysql.pass");
        this.mysqlDB = prop.getProperty("mysql.db");

        this.cloudURL = prop.getProperty("cloud.url");
        this.cloudUser = prop.getProperty("cloud.user");
        this.cloudPass = prop.getProperty("cloud.pass");
    }

    /**
     * Simple getter.
     * @return {@link #mysqlHost}.
     */
    public String getMysqlHost() {
        return mysqlHost;
    }

    /**
     * Simple getter.
     * @return {@link #mysqlUser}.
     */
    public String getMysqlUser() {
        return mysqlUser;
    }

    /**
     * Simple getter.
     * @return {@link #mysqlPass}.
     */
    public String getMysqlPass() {
        return mysqlPass;
    }

    /**
     * Simple getter.
     * @return {@link #mysqlDB}.
     */
    public String getMysqlDB() {
        return mysqlDB;
    }

    /**
     * Simple getter.
     * @return {@link #cloudURL}.
     */
    public String getCloudURL() {
        return cloudURL;
    }

    /**
     * Simple getter.
     * @return {@link #cloudUser}.
     */
    public String getCloudUser() {
        return cloudUser;
    }

    /**
     * Simple getter.
     * @return {@link #cloudPass}.
     */
    public String getCloudPass() {
        return cloudPass;
    }

    /**
     * Used to represent an inability to create a valid config.
     */
    public class ConfigException extends Exception {
        /**
         * Default constructor.
         * @param cause :  The cause of the config error.
         */
        ConfigException(final Throwable cause) {
            super(cause);
        }
    }
}

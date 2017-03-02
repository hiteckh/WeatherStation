package org.kentuni.WeatherStation.DatabaseUploader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public String mysqlHost = null;
    public String mysqlUser = null;
    public String mysqlPass = null;
    public String mysqlDB   = null;

    public String cloudURL  = null;
    public String cloudUser = null;
    public String cloudPass = null;

    public Config(String filename) throws ConfigError {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(filename));
        } catch (Exception e) {
            throw new ConfigError(e);
        }

        this.mysqlHost = prop.getProperty("mysql.host");
        this.mysqlUser = prop.getProperty("mysql.user");
        this.mysqlPass = prop.getProperty("mysql.pass");
        this.mysqlDB = prop.getProperty("mysql.db");

        this.cloudURL = prop.getProperty("cloud.url");
        this.cloudUser = prop.getProperty("cloud.user");
        this.cloudPass = prop.getProperty("cloud.pass");
    }

    public class ConfigError extends Exception {
        Exception e = null;

        public ConfigError(Exception e) {
            this.e = e;
        }
    }
}

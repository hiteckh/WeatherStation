package src.org.bluej.WeatherStation.DatabaseUploader;

/**
 * Main method for the database uploader to be used with little setup.
 * Utility class.
 */
public final class Main {

    /**
     * Utility class.
     */
    private Main() {
    }

    /**
     * Starts uploading to the cloud.
     * @param args First argument read as path to properties file.
     * @throws Config.ConfigException Thrown if unable to parse given properties file.
     * @throws ClassNotFoundException Thrown if unable to find valid database drivers.
     */
    public static void main(final String[] args) throws Config.ConfigException, ClassNotFoundException {
        if (args.length != 1) {
            Main.printUsage();
            return;
        }
        final String filename = args[0];
        final Config config = new Config(filename);
        final Thread uploader = new Uploader(config);
        uploader.start();
    }

    /**
     * Prints the usage of the database uploader.
     */
    private static void printUsage() {
        System.out.println("DatabaseUploader configFile");
    }
}


package org.kentuni.WeatherStation.DatabaseUploader;

public class Main {
    public static void main(String[] args) throws Exception {
        String filename = "";
        try {
            filename = args[0];
        } catch (Exception e) {
            Main.printUseage();
            return;
        }

        final Thread uploader = new Uploader(new Config(filename));
        uploader.start();
    }

    static void printUseage() {
        System.out.println("DatabaseUploader configFile");
    }
}


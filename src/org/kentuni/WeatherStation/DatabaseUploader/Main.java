package org.kentuni.WeatherStation.DatabaseUploader;

public class Main {
    public static void main(String[] args) {
        String filename = "";
        try {
            filename = args[0];
        } catch (Exception e) {
            Main.printUseage();
            return;
        }

        final Thread uploader = new Uploader(new Config("databases.config"));
        uploader.start();
    }

    static void printUseage() {
        System.out.println("DatabaseUploader configFile");
    }
}


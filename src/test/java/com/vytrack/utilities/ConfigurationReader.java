package com.vytrack.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//ConfigurationReader.configFile.getProperty("browser") direct access
//ConfigurationReader.getProperty("browser")
public class ConfigurationReader {
    //this class will be responsible for loading properties file and will provide access
    //to values based on key names
    //we use Properties class to load custom .properties files
    private static ThreadLocal<Properties> configFile = new ThreadLocal<>();

    public static String getProperty(String key) {
        try {
            if (configFile.get() == null) {
                configFile.set(new Properties());
            }
            configFile.get().load(new FileInputStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }
        return configFile.get().getProperty(key);
    }

}

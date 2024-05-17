package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocalConfig {

    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = LocalConfig.class.getClassLoader().getResourceAsStream("localconfig.properties")) {
            if (input == null) {
                System.out.println("Sorry. Unable to find localconfig.properties");
            } else {
                properties.load(input);
            }
        } catch(IOException e) {
                e.printStackTrace();
            }
        }

        public static String getProperty(String key) {
        return properties.getProperty(key);
        }

    public static String BASE_URI = getProperty("BASE_URI");
    public static String FETCH_API_RESPONSE = getProperty("FETCH_API_RESPONSE");

}

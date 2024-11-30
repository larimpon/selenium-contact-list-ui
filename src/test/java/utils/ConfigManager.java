package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class loads the values of the config.properties file that can be accessed by calling
 * ConfigManager.getProperty("<property_name>").
 */
public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get property file variable values.
     *
     * @param key   -   Variable name.
     * @return      -   Variable value.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
package utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties;

    static {
            String filePath = "/src/test/resources/config.properties";
            properties = loadProperty(filePath);
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Properties loadProperty(String filePath) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileReader(System.getProperty("user.dir") + filePath));
            System.out.println(filePath + " found");
        } catch (Exception e) {
            properties.clear();
            System.out.println(filePath + " not found");
        }
        return properties;
    }
}

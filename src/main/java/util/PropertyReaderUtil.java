package util;

import controller.AppController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderUtil {
    public static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream stream = PropertyReaderUtil.class.getResourceAsStream("/app.properties")) {
            properties.load(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}

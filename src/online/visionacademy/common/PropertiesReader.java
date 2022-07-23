package online.visionacademy.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private String filePath;
    private Properties props = new Properties();

    public PropertiesReader(String filePath) {
        this.filePath = filePath;
        load();
    }

    private final Properties load() {

        try (InputStream input = new FileInputStream(filePath)) {
            props.load(input);
            return props;
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return null;
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}

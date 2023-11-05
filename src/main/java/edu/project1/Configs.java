package edu.project1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// singleton configs
public class Configs {
    private Properties properties;
    private static final Configs INSTANCE = new Configs();
    private static final Logger LOGGER = LogManager.getLogger();

    private Configs() {
        properties = new Properties();
    }

    public void load(String filename) {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Configs getInstance() {
        return INSTANCE;
    }
}

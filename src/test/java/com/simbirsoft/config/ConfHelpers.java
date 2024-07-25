package com.simbirsoft.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfHelpers {
    private static final Logger logger = LoggerFactory.getLogger(ConfHelpers.class);
    protected static FileInputStream fileInputStream;
    protected static Properties properties;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
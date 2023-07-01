package com.assignment.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigUtil {
    public static String readProperty(String key) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}

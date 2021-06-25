package com.assignment.utils;

import java.util.Properties;

public class ConfigPropertiesUtil {
    public static String GOOGLE_URL;
    public static String FACEBOOK_URL;

    public static String POST_TEXT;

    public static String USER_EMAIL;
    public static String PASSWORD;

    public interface ConfigElements {
        static final String GOOGLE_URL = "GOOGLE";
        static final String FACEBOOK_URL = "FACEBOOK";
        static final String POST_TEXT = "POST_TEXT";
        static final String USER_EMAIL = "USER_EMAIL";
        static final String PASSWORD = "PASSWORD";
    }

    static {
        Properties configProperties = Configuration.loadProperties(Configuration.PROPERTY_FILE_NAME);

        GOOGLE_URL = configProperties.getProperty(ConfigElements.GOOGLE_URL);
        FACEBOOK_URL = configProperties.getProperty(ConfigElements.FACEBOOK_URL);
        POST_TEXT = configProperties.getProperty(ConfigElements.POST_TEXT);
        USER_EMAIL = configProperties.getProperty(ConfigElements.USER_EMAIL);
        PASSWORD = configProperties.getProperty(ConfigElements.PASSWORD);
    }
}

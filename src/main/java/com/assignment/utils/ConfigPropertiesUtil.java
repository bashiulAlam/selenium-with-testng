package com.assignment.utils;

import java.util.Properties;

public class ConfigPropertiesUtil {
    //facebook assignment config variables
    public static String GOOGLE_URL;
    public static String FACEBOOK_URL;

    public static String POST_TEXT;

    public static String USER_EMAIL;
    public static String PASSWORD;

    //test insurance company assignment variables
    public static String TEST_INSURANCE_COMPANY_URL;

    public interface ConfigElements {
        //facebook elements
        static final String GOOGLE_URL = "GOOGLE";
        static final String FACEBOOK_URL = "FACEBOOK";
        static final String POST_TEXT = "POST_TEXT";
        static final String USER_EMAIL = "USER_EMAIL";
        static final String PASSWORD = "PASSWORD";

        //test insurance company elements
        static final String TEST_INSURANCE_COMPANY_URL = "TEST_INSURANCE_COMPANY_URL";
    }

    static {
        //load property file
        Properties configProperties = Configuration.loadProperties(Configuration.PROPERTY_FILE_NAME);

        //assign facebook assignment variables
        GOOGLE_URL = configProperties.getProperty(ConfigElements.GOOGLE_URL);
        FACEBOOK_URL = configProperties.getProperty(ConfigElements.FACEBOOK_URL);
        POST_TEXT = configProperties.getProperty(ConfigElements.POST_TEXT);
        USER_EMAIL = configProperties.getProperty(ConfigElements.USER_EMAIL);
        PASSWORD = configProperties.getProperty(ConfigElements.PASSWORD);

        //assign test insurance company assignment variables
        TEST_INSURANCE_COMPANY_URL = configProperties.getProperty(ConfigElements.TEST_INSURANCE_COMPANY_URL);
    }
}

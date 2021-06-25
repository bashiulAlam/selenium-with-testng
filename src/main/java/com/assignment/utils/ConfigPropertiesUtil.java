package com.assignment.utils;

import java.util.Properties;

public class ConfigPropertiesUtil {
    //facebook assignment config variables
    public static String GOOGLE_URL;
    public static String FACEBOOK_URL;

    public static String POST_TEXT;

    public static String FACEBOOK_USER_EMAIL;
    public static String FACEBOOK_PASSWORD;

    //test insurance company assignment variables
    public static String TEST_INSURANCE_COMPANY_URL;

    public static String INSURANCE_USER_EMAIL;
    public static String INSURANCE_PASSWORD;
    public static String INSURANCE_USERNAME;

    public static String RATING;
    public static String REVIEW;

    public interface ConfigElements {
        //facebook elements
        static final String GOOGLE_URL = "GOOGLE";
        static final String FACEBOOK_URL = "FACEBOOK";
        static final String POST_TEXT = "POST_TEXT";
        static final String FACEBOOK_USER_EMAIL = "FACEBOOK_USER_EMAIL";
        static final String FACEBOOK_PASSWORD = "FACEBOOK_PASSWORD";

        //test insurance company elements
        static final String TEST_INSURANCE_COMPANY_URL = "TEST_INSURANCE_COMPANY";
        static final String INSURANCE_USER_EMAIL = "INSURANCE_USER_EMAIL";
        static final String INSURANCE_PASSWORD = "INSURANCE_PASSWORD";
        static final String INSURANCE_USERNAME = "INSURANCE_USERNAME";
        static final String REVIEW = "REVIEW";
        static final String RATING = "RATING";
    }

    static {
        //load property file
        Properties configProperties = Configuration.loadProperties(Configuration.PROPERTY_FILE_NAME);

        //assign facebook assignment variables
        GOOGLE_URL = configProperties.getProperty(ConfigElements.GOOGLE_URL);
        FACEBOOK_URL = configProperties.getProperty(ConfigElements.FACEBOOK_URL);
        POST_TEXT = configProperties.getProperty(ConfigElements.POST_TEXT);
        FACEBOOK_USER_EMAIL = configProperties.getProperty(ConfigElements.FACEBOOK_USER_EMAIL);
        FACEBOOK_PASSWORD = configProperties.getProperty(ConfigElements.FACEBOOK_PASSWORD);

        //assign test insurance company assignment variables
        TEST_INSURANCE_COMPANY_URL = configProperties.getProperty(ConfigElements.TEST_INSURANCE_COMPANY_URL);
        INSURANCE_USER_EMAIL = configProperties.getProperty(ConfigElements.INSURANCE_USER_EMAIL);
        INSURANCE_PASSWORD = configProperties.getProperty(ConfigElements.INSURANCE_PASSWORD);
        INSURANCE_USERNAME = configProperties.getProperty(ConfigElements.INSURANCE_USERNAME);
        REVIEW = configProperties.getProperty(ConfigElements.REVIEW);
        RATING = configProperties.getProperty(ConfigElements.RATING);
    }
}

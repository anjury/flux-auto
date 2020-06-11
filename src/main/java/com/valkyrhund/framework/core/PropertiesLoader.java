package com.valkyrhund.framework.core;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public Properties loadPropertiesFile(String fileName) {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("The properties file, '" + fileName + "', was not found in the classpath.");
            }
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

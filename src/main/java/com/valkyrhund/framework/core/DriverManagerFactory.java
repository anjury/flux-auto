package com.valkyrhund.framework.core;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(DriverManagerType type) {
        DriverManager driverManager;
        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                System.out.println("No valid Driver Manager specified, defaulting to Chrome.");
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}

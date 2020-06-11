package com.valkyrhund.framework.core;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(DriverManagerType type) {
        DriverManager driverManager;
        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            // not implemented
//            case EDGE:
//                break;
//            case FIREFOX:
//                break;
//            case IE:
//                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}

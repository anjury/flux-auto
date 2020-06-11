package com.valkyrhund.framework.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void createWebDriver() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.getInstance(FIREFOX).setup();
        this.driver = new FirefoxDriver(options);
    }

}

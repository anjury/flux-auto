package com.valkyrhund.framework.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromeDriverManager extends DriverManager {

    @Override
    public void createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.getInstance(CHROME).setup();
        this.driver = new ChromeDriver(options);
    }

}

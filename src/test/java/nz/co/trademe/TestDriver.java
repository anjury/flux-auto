package nz.co.trademe;

import com.valkyrhund.framework.core.DriverManager;
import com.valkyrhund.framework.core.DriverManagerFactory;
import com.valkyrhund.framework.core.PropertiesLoader;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class TestDriver {

    public static DriverManager driverManager;
    public static WebDriver driver;
    final public Properties properties;
    final public String runTarget;

    // load properties file and set up Web Driver / Manager, if executing a web UI test suite

    public TestDriver() {
        properties = new PropertiesLoader().loadPropertiesFile("runtime.properties");
        runTarget = properties.getProperty("run_target");
        if (runTarget.equalsIgnoreCase("web")) {
            driverManager = DriverManagerFactory.getDriverManager(CHROME);
            driver = driverManager.getWebDriver();
        }
    }

    // Quit the Web Driver, if one exists

    public void cleanup() {
        if (driverManager != null) {
            driverManager.quitWebDriver();
        }
    }

}

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

    public TestDriver() {
        properties = new PropertiesLoader().loadPropertiesFile("runtime.properties");
        runTarget = getPrioritisedProperty("run_target");
        if (runTarget.equalsIgnoreCase("web")) {
            driverManager = DriverManagerFactory.getDriverManager(CHROME);
            driver = driverManager.getWebDriver();
        }
    }

    public String getPrioritisedProperty(String propertyName) {
        if (System.getenv().containsKey(propertyName)) {
            return System.getenv(propertyName);
        } else if (System.getenv().containsKey(propertyName.toUpperCase())) {
            return System.getenv(propertyName.toUpperCase());
        } else {
            return properties.getProperty(propertyName);
        }
    }

    public void cleanup() {
        if (driverManager != null) {
            driverManager.quitWebDriver();
        }
    }

}

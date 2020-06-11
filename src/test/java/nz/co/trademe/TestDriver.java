package nz.co.trademe;

import com.valkyrhund.framework.core.DriverManager;
import com.valkyrhund.framework.core.DriverManagerFactory;
import com.valkyrhund.framework.core.PropertiesLoader;
import nz.co.trademe.step_definitions.Hooks;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;

public class TestDriver {

    public static DriverManager driverManager;
    public static WebDriver driver;
    public Properties properties;
    public String runTarget;

    public TestDriver() {
        // Load the runtime properties file
        properties = new PropertiesLoader().loadPropertiesFile("runtime.properties");
        runTarget = getPrioritisedProperty("run_target");
        if (runTarget.equalsIgnoreCase("web")) {
            // Testing using the Firefox driver, but not fully implemented yet
//            driverManager = DriverManagerFactory.getDriverManager(FIREFOX);
            driverManager = DriverManagerFactory.getDriverManager(CHROME);
            driver = driverManager.getWebDriver();
        }
    }

    public String getPrioritisedProperty(String propertyName) {
        // If there is an environment property set we use that and ignore the properties file
        if (System.getenv().containsKey(propertyName)) {
            return System.getenv(propertyName);
        } else if (System.getenv().containsKey(propertyName.toUpperCase())) {
            // Windows sometimes translates property names to upper case
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

package nz.co.trademe.step_definitions;

import nz.co.trademe.TestDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    public static TestDriver testDriver;
    public static Scenario scenario;

    @Before
    public void before(Scenario beforeScenario) throws Throwable {
        testDriver = new TestDriver();
        scenario = beforeScenario;
    }

    @After
    public void after() throws Throwable {
        testDriver.cleanup();
    }

}

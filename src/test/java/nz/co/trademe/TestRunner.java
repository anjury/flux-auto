package nz.co.trademe;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

// Note To Self: These options are only acted on if TestRunner is executed, not if features or tests are run in the UI
// or via Maven.

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/java/nz/co/trademe/domain/features"},
    glue = {"nz.co.trademe.step_definitions"},
//    plugin = {"pretty", "summary"},
    snippets = SnippetType.CAMELCASE,
    strict = true
)
public class TestRunner {
    // @BeforeAll and @AfterAll methods
}
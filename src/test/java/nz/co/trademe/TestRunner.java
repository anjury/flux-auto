package nz.co.trademe;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/java/nz/co/trademe/features"},
    glue = {"nz.co.trademe.step_definitions"},
    snippets = SnippetType.CAMELCASE,
    strict = true
)
public class TestRunner {
}
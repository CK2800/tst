package cucumber;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, dryRun = true, features = "src/test/resources/features/")
public class RunCucumberTest {
}
package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks", "utilities"},
        tags = "@KontrolTest",
        plugin = {
                "pretty"
        },
        objectFactory = io.cucumber.guice.GuiceFactory.class
)
public class SmokeRunnerTest extends AbstractTestNGCucumberTests {
}

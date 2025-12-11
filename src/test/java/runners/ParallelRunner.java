package runners;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks", "utilities"},
        plugin = {"html:target/report-parallel/report.html"}
)
public class ParallelRunner {
}

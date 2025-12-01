package runners;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        plugin = {
                "html:target/report-parallel/report.html"
        },
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"}
)

public class ParallelRunner {
}
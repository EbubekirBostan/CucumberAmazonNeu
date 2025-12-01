package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions","hooks"},
        tags = "@smoke",
        plugin = {"json:target/cucumber-report/cucumber.json",
                "pretty"}
        // raporlari olustururken format:path seklinde raporun nerede ve hangi formatta olusturacagimizi belirtiriz
)
@Test
public class SmokeRunner extends AbstractTestNGCucumberTests {
}
package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.FrameworkModule;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks", "utilities"},
        tags = "@smoke",
        plugin = {
               // "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty"
        },
        objectFactory = io.cucumber.guice.GuiceFactory.class

)

@Test
public class SmokeRunner extends AbstractTestNGCucumberTests {
}

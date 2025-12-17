package hooks;

import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Inject
    private WebDriver driver;

    @Before
    public void setup() {
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            // screenshot al ve allure attach
             byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
             Allure.addAttachment("screenshot", new ByteArrayInputStream(screenshot));
        }
        //driver.quit();
    }
}
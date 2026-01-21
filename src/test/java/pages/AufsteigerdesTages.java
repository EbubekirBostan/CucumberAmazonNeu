package pages;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ReusableMethods;

@ScenarioScoped
public class AufsteigerdesTages {
    private final WebDriver driver;

    private final ReusableMethods reusableMethods;


    @Inject
    public AufsteigerdesTages(WebDriver driver, ReusableMethods reusableMethods) {
        this.driver = driver;
        this.reusableMethods = reusableMethods;
    }






}

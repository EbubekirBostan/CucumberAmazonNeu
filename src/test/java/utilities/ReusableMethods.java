package utilities;

import com.google.inject.Inject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReusableMethods {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    @Inject
    public ReusableMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void goToBaseURL() {
        driver.get(ConfigReader.getProperty("URL"));
    }
    public void goToMain(WebElement element, WebElement goToMn, String text) {
        boolean elementExists;
        try { elementExists = element.isDisplayed();
        }
        catch (Exception e) { elementExists = false;
        } if (elementExists)
        { if (element.getText().trim().equalsIgnoreCase(text.trim()))
        { goToMn.click(); }
        } else { }
    }

    public void clickByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollIntoViewJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public List<String> getElementsText(List<WebElement> list) {
        List<String> textList = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) textList.add(el.getText());
        }
        return textList;
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitAndClick(WebElement element) {
        waitForClickability(element).click();
    }

    public void waitAndSendText(WebElement element, String text) {
        waitForVisibility(element).sendKeys(text);
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public WebElement selectRandomText(Select select) {
        List<WebElement> options = select.getOptions();
        int randomIndex = new Random().nextInt(options.size());
        select.selectByIndex(randomIndex);
        return select.getFirstSelectedOption();
    }
}

package utilities;

import com.google.inject.Inject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReusableMethods {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    private final Random random;

    @Inject
    public ReusableMethods(WebDriver driver, Random random) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.random = random;
    }

    public void goToBaseURL() {
        driver.get(ConfigReader.getProperty("URL"));
    }
    public void clickIfTextMatches(By textLocator, By buttonLocator, String expectedText) {

        try {
            WebElement textElement = waitForVisibility(textLocator);

            if (textElement.getText().trim().equalsIgnoreCase(expectedText.trim())) {
                waitAndClick(buttonLocator);
            }
        } catch (Exception ignored) {
            // element yoksa sessizce geç
        }
    }

    /*public int getSoldCountSafely(WebElement product) {
        try {
            String text = product.findElement(soldInfo).getText();
            return parseSoldCount(text);
        } catch (NoSuchElementException e) {
            return -1; // satılma bilgisi yok
        }
    }*/
    public int parseSoldCount(String text) {

        if (text == null || text.isBlank()) {
            return 0;
        }

        // Sadece sayı + B/K kalsın
        String cleaned = text.replaceAll("[^0-9BKbk.]", "");

        if (cleaned.isEmpty()) {
            return 0;
        }

        if (cleaned.toUpperCase().contains("B")) {
            return (int) (Double.parseDouble(cleaned.replaceAll("[BKbk]", "")) * 1000);
        }

        if (cleaned.toUpperCase().contains("K")) {
            return (int) (Double.parseDouble(cleaned.replaceAll("[BKbk]", "")) * 1000);
        }

        return Integer.parseInt(cleaned);
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
    public  WebElement element (By locator){

        return driver.findElement(locator);
    }

    public WebElement waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitAndClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void waitAndSendKeys(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
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

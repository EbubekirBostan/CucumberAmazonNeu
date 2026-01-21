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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public int parseSoldCount(String text) {

        if (text == null || text.isBlank()) {
            return 0;
        }

        // "5 B", "20 B", "400" gibi değerleri yakalar
        Pattern pattern = Pattern.compile("(\\d+(?:[.,]\\d+)?)\\s*(B)?");
        Matcher matcher = pattern.matcher(text);

        if (!matcher.find()) {
            return 0; // hiçbir sayı yoksa
        }

        String numberPart = matcher.group(1).replace(",", ".");
        boolean isThousand = matcher.group(2) != null;

        double value = Double.parseDouble(numberPart);

        if (isThousand) {
            value *= 1000;
        }

        return (int) value;
    }
    public int parseSoldCountDE(String text) {

        if (text == null || text.isBlank()) {
            return 0;
        }

        // 700+, 7000+, 1000+ yakalar
        Pattern pattern = Pattern.compile("(\\d+)\\+");
        Matcher matcher = pattern.matcher(text);

        if (!matcher.find()) {
            return 0;
        }

        return Integer.parseInt(matcher.group(1));
    }





    public void clickByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollIntoViewJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hover(By locator) {
        WebElement el = waitForVisibility(locator);
        actions.moveToElement(el).pause(Duration.ofMillis(300)).perform();

    }
    public void actionsClick(By locator) {
        actions.click(element(locator)).perform();

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
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(locator)
        )).click();
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

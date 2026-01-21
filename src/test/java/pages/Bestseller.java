package pages;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.*;
import org.testng.Assert;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ScenarioScoped
public class Bestseller {

    private final WebDriver driver;
    private final ReusableMethods reusableMethods;

    private final Random random = new Random();
    private final List<Integer> soldCounts = new ArrayList<>();

    @Inject
    public Bestseller(WebDriver driver, ReusableMethods reusableMethods) {
        this.driver = driver;
        this.reusableMethods = reusableMethods;
    }


    private final By linkAlle = By.id("nav-hamburger-menu");
    private final By linkBestseller = By.linkText("Bestseller");
    private final By linkWeitere = By.linkText("Weitere");
    private final By listProdukte = By.cssSelector(".zg-no-numbers");
    private final By verkauftInfo = By.cssSelector("#social-proofing-faceout-title-tk_bought");



    public void klicktAlle() {
        reusableMethods.waitAndClick(linkAlle);
    }

    public void klicktBestseller() {
        reusableMethods.waitAndClick(linkBestseller);
    }

    public void klicktWeitere() {
        reusableMethods.waitAndClick(linkWeitere);
    }

    public void klicktRandomProduktundGetSoldCounts(int clickCount) {

        int clicked = 0;

        while (clicked < clickCount) {

            List<WebElement> products = driver.findElements(listProdukte);

            if (products.isEmpty()) {
                throw new RuntimeException("Produktliste ist leer!");
            }

            if (clicked >= products.size()) {
                break;
            }

            try {
                WebElement product = products.get(clicked);
                product.click();

                List<WebElement> soldInfoList = driver.findElements(verkauftInfo);

                if (!soldInfoList.isEmpty()) {
                    String soldText = soldInfoList.get(0).getText();
                    int count = reusableMethods.parseSoldCountDE(soldText);
                    soldCounts.add(count);

                    System.out.println("Verkaufsmenge: " + count);
                } else {
                    System.out.println("Keine Verkaufsinformation vorhanden");
                }

            } catch (StaleElementReferenceException e) {
                continue;
            }

            driver.navigate().back();
            clicked++;
        }
    }



    public void verifyPercentageSold(int prozent, int minVer) {

        if (soldCounts.isEmpty()) {
            Assert.fail("Keine Produkte mit Verkaufsinformationen gefunden.");
        }

        long geeigneteProdukte = soldCounts.stream()
                .filter(count -> count > minVer)
                .count();

        double anteil = (geeigneteProdukte * 100.0) / soldCounts.size();

        System.out.println("Produkte mit Verkaufsdaten insgesamt: " + soldCounts.size());
        System.out.println("Produkte mit mehr als " + minVer + " Verkäufen: " + geeigneteProdukte);
        System.out.println("Anteil: %" + anteil);

        Assert.assertTrue(
                anteil >= prozent,
                "Erwartet: ≥ %" + prozent +
                        ", Tatsächlich: %" + anteil
        );
    }
}

package pages;

import com.google.inject.Inject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utilities.ReusableMethods;

import java.util.*;

public class Bestseller {

    private final WebDriver driver;

    private final ReusableMethods reusableMethods;

    private Set<Integer> klickedProdukte = new HashSet<>();
    Random random = new Random();

    @Inject
    public Bestseller(WebDriver driver, ReusableMethods reusableMethods) {
        this.driver = driver;
        this.reusableMethods = reusableMethods;
    }


    private final By linkAlle = By.id("nav-hamburger-menu");
    private final By linkBestseller = By.linkText("Çok Satanlar");
    private final By linkWeitere = By.xpath("//a[@aria-label='Oyuncaklar ve Oyunlar Listesinde Çok Satanlar - Daha Fazla Göster']");
    private final By listProdukte = By.cssSelector(".zg-no-numbers");
    private final By verkauftInfo = By.cssSelector("#social-proofing-faceout-title-tk_bought");
    private List<Integer> soldCounts = new ArrayList<>();
    private Set<String> verkauftText = new HashSet<>();





    public void klicktAlle(){

        System.out.println("cerez red");
        reusableMethods.waitForVisibility(linkAlle).click();

    }
    public  void klicktBestseller(){
        driver.findElement(linkBestseller).click();
    }
    public void klicktWeitere(){
        driver.findElement(linkWeitere).click();

    }

    public void klicktRandomProduktundGetSoldCounts(int clickCount){
        //System.out.println("tıklandı");

        int clicked = 0;

        while (clicked < clickCount) {

            List<WebElement> products = driver.findElements(listProdukte);

            if (products.isEmpty()) {
                throw new RuntimeException("Ürün listesi boş!");
            }

            if (clicked >= products.size()) {
                break; // ürün sayısı 20’den azsa
            }

            WebElement product = products.get(clicked);

            try {
                product.click();
                verkauftText.add(driver.findElement(verkauftInfo).getText());
                for (int i = 0; i <verkauftText.size() ; i++) {
                    System.out.println(verkauftText);
                }

            } catch (StaleElementReferenceException e) {
                continue; // stale olduysa tekrar dene
            }

            driver.navigate().back();

            clicked++;
        }
    }

}








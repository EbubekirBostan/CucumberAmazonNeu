package pages;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utilities.ReusableMethods;

import java.util.*;
import java.util.stream.Collectors;
@ScenarioScoped
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

    public void klicktRandomProduktundGetSoldCounts(int clickCount) {

        int clicked = 0;

        while (clicked < clickCount) {

            List<WebElement> products = driver.findElements(listProdukte);

            if (products.isEmpty()) {
                throw new RuntimeException("Ürün listesi boş!");
            }

            if (clicked >= products.size()) {
                break;
            }

            try {
                WebElement product = products.get(clicked);
                product.click();

                // 🟢 SATIŞ BİLGİSİ OPSİYONEL
                List<WebElement> soldInfoList = driver.findElements(verkauftInfo);

                if (!soldInfoList.isEmpty()) {
                    String soldText = soldInfoList.get(0).getText();
                    verkauftText.add(soldText);
                    System.out.println("Satış bilgisi: " + soldText);
                } else {
                    verkauftText.add("SATIŞ BİLGİSİ YOK");
                    System.out.println("Satış bilgisi bulunamadı.");
                }

            } catch (StaleElementReferenceException e) {
                continue;
            }

            driver.navigate().back();
            clicked++;
        }

    }

    public void verifyVerkauftInfo(int minVer, int menge){
        System.out.println(">>> verifyVerkauftInfo METHODU ÇALIŞTI <<<");

        System.out.println("verkauftText size = " + verkauftText.size());

        Set<String> temizListe = verkauftText.stream()
                .filter(text -> text.matches(".*\\d+.*"))
                .collect(Collectors.toSet());

        System.out.println("temizListe size = " + temizListe.size());



        List<Integer> soldCounts = new ArrayList<>();

        for (String text : temizListe) {
            int count = reusableMethods.parseSoldCount(text);
            soldCounts.add(count);
        }
        System.out.println("------ SATIŞ SAYILARI ------");
        soldCounts.forEach(System.out::println);

        long count = soldCounts.stream()
                .filter(s -> s >= minVer)
                .count();

        Assert.assertTrue(
                count >= menge,
                "Beklenen: en az " + menge +
                        " ürün " + minVer +
                        " satış, ama bulunan: " + count
        );




    }


}








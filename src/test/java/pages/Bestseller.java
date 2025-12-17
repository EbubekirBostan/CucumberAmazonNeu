package pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private final By linkWeitere = By.linkText("Daha Fazla Göster");
    private final By listProdukte = By.cssSelector(".zg-no-numbers");
    private final By verkauftInfo = By.cssSelector("#social-proofing-faceout-title-tk_bought");
    private List<Integer> soldCounts = new ArrayList<>();




    public void klicktAlle(){
        driver.findElement(linkAlle).click();
    }
    public  void klicktBestseller(){
        driver.findElement(linkBestseller).click();
    }
    public void klicktWeitere(){
        driver.findElement(linkWeitere);
    }
    public List<Integer> klicktRandomProduktundGetSoldCounts(){
        List<WebElement> produkte = driver.findElements(listProdukte);
        int produkteSize = produkte.size();
        int howManyToClick = new Random().nextInt(produkteSize) + 1;
        Set<Integer> usedIndexes = new HashSet<>();
        List<Integer> soldCounts = new ArrayList<>();


        return soldCounts;
    }





}

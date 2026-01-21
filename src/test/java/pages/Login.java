package pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.ReusableMethods;

public class Login {

    private final WebDriver driver;
    private final ReusableMethods reusableMethods;

    @Inject
    public Login(WebDriver driver, ReusableMethods reusableMethods) {
        this.driver = driver;
        this.reusableMethods = reusableMethods;
        PageFactory.initElements(driver, this);
    }

    private final By sprachen = By.className("icp-nav-link-inner");

    private final By sprachDeutsch = By.xpath("(//a[@href='#switch-lang=de_DE'])[1]");


    private final By weiterText = By.xpath("//div/h4");

    private final By cerezLocater = By.xpath("//div/h4");

    private final By weiterButton = By.xpath("//span/button");

    private final By linkSignIn = By.xpath("(//div/a)[4]");


    private final By ablehnen = By.id("sp-cc-rejectall-link");

    private final By emailInput = By.xpath("//input[@name='email']");

    private final By buttonContinue = By.id("continue");

    private final By passwordInput = By.id("ap_password");

    private final By buttonSignIn = By.id("signInSubmit");

    private final By personalAccount = By.id("nav-link-accountList-nav-line-1");

    private final By meldenFehlerAlert = By.xpath("//div/h1");

    private final By keinAccountAlert = By.xpath("(//div[@class='a-alert-content'])[1]");
    private final By falschOderUngultigeEmailAlert = By.xpath("(//div[@class='a-alert-content'])[4]");
    private final By ungultigeEmailAlert = By.xpath("(//div[@class='a-alert-content'])[3]");

    public String h4Text = "Klicke auf die Schaltfläche unten, um mit dem Einkauf fortzufahren";
    public String cerezText = "\n" +
            "            Çerezler ve reklam seçenekleri\n" +
            "        ";
    public String meldenWarnungMessage = "Sieht so aus, als wärst du neu bei Amazon";
    public String fehlerEmailAlertMessage = "Geçersiz e-posta adresi";

    public String keinAccountText = "Es konnte kein Konto mit dieser E-Mail-Adresse gefunden werden.";



    public void goToUrl(){
        reusableMethods.goToBaseURL();
    }
    public void wahltSprach(){
        reusableMethods.hover(sprachen);
        WebElement de = reusableMethods.waitForVisibility(sprachDeutsch);
        reusableMethods.clickByJS(de);

    }

    public void ablehnenCookie(){
        reusableMethods.clickIfTextMatches(weiterText,weiterButton,h4Text);
        reusableMethods.clickIfTextMatches(cerezLocater,ablehnen,cerezText);
    }

    public void signInClick(){

        reusableMethods.element(linkSignIn).click();
    }

    public void sendKeysEmail(String email){
        reusableMethods.waitForVisibility(emailInput);
       reusableMethods.element(emailInput).sendKeys(ConfigReader.getProperty(email));
    }

    public void buttonContinueClick(){
        reusableMethods.element(buttonContinue).click();
    }

    public void sendKeysPassword(String password){
        reusableMethods.element(passwordInput).sendKeys(ConfigReader.getProperty(password));
    }

    public void signInButtonClick(){
        reusableMethods.element(buttonSignIn).click();
    }

    public void verifyLogin(){
        reusableMethods.waitForVisibility(personalAccount);
        Assert.assertTrue(reusableMethods.element(personalAccount).getText().contains("Hallo, Mkemal"));
    }

    public void verifySignInErrorMessage(){
        Assert.assertTrue(reusableMethods.element(meldenFehlerAlert).getText().contains(meldenWarnungMessage)||reusableMethods.element(keinAccountAlert).getText().contains(keinAccountText));
    }

    public void verifySignInFehlerEmailAlert(){
        Assert.assertTrue(reusableMethods.element(falschOderUngultigeEmailAlert).getText().toLowerCase().contains(fehlerEmailAlertMessage.toLowerCase())||reusableMethods.element(ungultigeEmailAlert).getText().toLowerCase().contains(fehlerEmailAlertMessage.toLowerCase()));
    }
}

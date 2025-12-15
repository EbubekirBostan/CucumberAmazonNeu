package pages;

import com.google.inject.Inject;
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

    @FindBy(xpath = "//div/h4")
    private WebElement weiterText;

    @FindBy(xpath = "//div/h4")
    private WebElement cerezLocater;

    @FindBy(xpath = "//span/button")
    private WebElement weiterButton;

    @FindBy(xpath = "(//div/a)[4]")
    private WebElement linkSignIn;

    @FindBy(id = "sp-cc-rejectall-link")
    private WebElement ablehnen;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(id = "continue")
    private WebElement buttonContinue;

    @FindBy(id = "ap_password")
    private WebElement passwordInput;

    @FindBy(id = "signInSubmit")
    private WebElement buttonSignIn;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement personalAccount;

    @FindBy(xpath = "//div/h1")
    private WebElement meldenFehlerAlert;

    @FindBy(id = "invalid-email-alert")
    private WebElement meldenFehlerEmailAlert;

    public String h4Text = "Klicke auf die Schaltfläche unten, um mit dem Einkauf fortzufahren";
    public String cerezText = "\n" +
            "            Çerezler ve reklam seçenekleri\n" +
            "        ";
    public String meldenWarnungMessage = "Görünüşe göre Amazon'da yenisiniz";
    public String fehlerEmailAlertMessage = "Geçersiz e-posta adresi";

    public void goToUrl(){
        reusableMethods.goToBaseURL();
    }

    public void signInClick(){
        reusableMethods.goToMain(weiterText,weiterButton,h4Text);
        reusableMethods.goToMain(cerezLocater,ablehnen,cerezText);
        linkSignIn.click();
    }

    public void sendKeysEmail(String email){
        reusableMethods.waitForVisibility(emailInput);
        emailInput.sendKeys(ConfigReader.getProperty(email));
    }

    public void buttonContinueClick(){
        buttonContinue.click();
    }

    public void sendKeysPassword(String password){
        passwordInput.sendKeys(ConfigReader.getProperty(password));
    }

    public void signInButtonClick(){
        buttonSignIn.click();
    }

    public void verifyLogin(){
        reusableMethods.waitForVisibility(personalAccount);
        Assert.assertTrue(personalAccount.getText().contains("Hallo, Mkemal"));
    }

    public void verifySignInErrorMessage(){
        Assert.assertTrue(meldenFehlerAlert.getText().contains(meldenWarnungMessage));
    }

    public void verifySignInFehlerEmailAlert(){
        Assert.assertTrue(meldenFehlerEmailAlert.getText().contains(fehlerEmailAlertMessage));
    }
}

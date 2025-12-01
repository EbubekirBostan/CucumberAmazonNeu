package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class Login {

    public Login() {
        PageFactory.initElements(Driver.getDriver(),this);
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

    @FindBy(id = "ap_email_login")
    private WebElement emailInput;


    @FindBy(id = "continue")
    private WebElement buttonContinue;

    @FindBy(id = "ap_password")
    private WebElement passwordInput;

    @FindBy(id = "signInSubmit")
    private WebElement buttonSignIn;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement personalAccount;

    @FindBy(id = "signin-error-msg")
    private WebElement signInErrorMsg;

    public String h4Text = "Klicke auf die Schaltfläche unten, um mit dem Einkauf fortzufahren";
    public String cerezText = "\n" +
            "            Çerezler ve reklam seçenekleri\n" +
            "        ";




    public void signInClick(){
        ReusableMethods.goToMain(weiterText,weiterButton,h4Text);
        ReusableMethods.goToMain(cerezLocater,ablehnen,cerezText);
        linkSignIn.click();

    }
    protected void sendKeysEmail(String email){
        emailInput.sendKeys(ConfigReader.getProperty(email));
    }
    public void buttonContinueClick(){
        buttonContinue.click();
    }
    protected void sendKeysPassword(String password){
        passwordInput.sendKeys(ConfigReader.getProperty(password));
    }
    public void signInButtonClick(){
        buttonSignIn.click();
    }
    public void verifyLogin(){
        Assert.assertTrue(personalAccount.getText().contains("Hallo, Mkemal"));
    }

    public void verifySignInErrorMessage(){

        Assert.assertTrue(signInErrorMsg.getText().contains("We ran into a problem. Please try again later."));
    }




}

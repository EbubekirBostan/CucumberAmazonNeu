package stepDefinitions;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Login;
import utilities.ReusableMethods;

public class LoginStepdefs {

    @Inject
    Login login;

    @Given("der Benutzer offnet die Startseite")
    public void derBenutzerOffnetDieStartseite() {
        login.goToUrl();
    }

    @And("der Benutzer klickt auf die Sign-In-Schaltflache")
    public void derBenutzerKlicktAufDieSignInSchaltflache() {
        login.signInClick();
    }

    @Given("der Benutzer gibt die E-Mail {string} ein und klickt auf Weiter")
    public void derBenutzerGibtDieEMailEinUndKlicktAufWeiter(String email) {
        login.sendKeysEmail(email);
        login.buttonContinueClick();
    }

    @And("der Benutzer gibt das Passwort {string} ein und klickt erneut auf Weiter")
    public void derBenutzerGibtDasPasswortEinUndKlicktErneutAufWeiter(String passwort) {
        login.sendKeysPassword(passwort);
        login.signInButtonClick();
    }

    @Then("der Benutzer sollte erfolgreich eingeloggt sein")
    public void derBenutzerSollteErfolgreichEingeloggtSein() {
        login.verifyLogin();
    }

    @Given("der Benutzer versucht sich mit der E-Mail {string} anzumelden")
    public void derBenutzerVersuchtSichMitDerEMailAnzumelden(String email) {
        login.sendKeysEmail(email);
    }

    @When("der Benutzer die Eingabe bestätigt")
    public void derBenutzerDieEingabeBestätigt() {
        login.buttonContinueClick();
    }

       @And("eine Fehlermeldung sollte angezeigt werden")
    public void eineFehlermeldungSollteAngezeigtWerden() {
        login.verifySignInErrorMessage();
    }


    @And("die Fehlermeldung sollte angezeigt werden")
    public void dieFehlermeldungSollteAngezeigtWerden() {
        login.verifySignInFehlerEmailAlert();
    }
}

package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Login;
import utilities.ReusableMethods;

public class LoginStepdefs extends Login {

    @Given("der Benutzer offnet die Startseite")
    public void derBenutzerOffnetDieStartseite() {
        ReusableMethods.goToBaseURL();
    }

    @And("der Benutzer klickt auf die Sign-In-Schaltflache")
    public void derBenutzerKlicktAufDieSignInSchaltflache() {
        signInClick();
    }

    @Given("der Benutzer gibt die E-Mail {string} ein und klickt auf Weiter")
    public void derBenutzerGibtDieEMailEinUndKlicktAufWeiter(String email) {
        sendKeysEmail(email);
        buttonContinueClick();
    }

    @And("der Benutzer gibt das Passwort {string} ein und klickt erneut auf Weiter")
    public void derBenutzerGibtDasPasswortEinUndKlicktErneutAufWeiter(String passwort) {
        sendKeysPassword(passwort);
        signInButtonClick();
    }

    @Then("der Benutzer sollte erfolgreich eingeloggt sein")
    public void derBenutzerSollteErfolgreichEingeloggtSein() {
    }

    @Given("der Benutzer versucht sich mit der E-Mail {string} anzumelden")
    public void derBenutzerVersuchtSichMitDerEMailAnzumelden(String email) {
    }

    @When("der Benutzer die Eingabe bestätigt")
    public void derBenutzerDieEingabeBestätigt() {
    }

    @Then("der Login-Vorgang sollte fehlschlagen")
    public void derLoginVorgangSollteFehlschlagen() {
    }

    @And("eine Fehlermeldung sollte angezeigt werden")
    public void eineFehlermeldungSollteAngezeigtWerden() {
    }



}

package stepDefinitions;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Bestseller;

public class BestsellerStepdefs {

    @Inject
    Bestseller bestseller;

    @Given("der Benutze klickt auf die Alle")
    public void derBenutzeKlicktAufDieAlle() {
        bestseller.klicktAlle();
    }

    @And("der Benutzer klickt auf die Bestseller")
    public void derBenutzerKlicktAufDieBestseller() {
        bestseller.klicktBestseller();
    }

    @And("der Benutze klickt neben dem Bestseller in Spielzug auf Weitere")
    public void derBenutzeKlicktNebenDemBestsellerInSpielzugAufWeitere() {
        bestseller.klicktWeitere();
    }

    @Then("der Benutzer klickt auf {int} beliebiges Produkt")
    public void derBenutzerKlicktAufBeliebigesProdukt(int arg0) {
        bestseller.klicktRandomProduktundGetSoldCounts(arg0);
    }

    @When("Der Nutzer bestätigt, dass mindestens {int} Stück der {int} Produkte verkauft wurden.")
    public void derNutzerBestätigtDassMindestensStückDerProdukteVerkauftWurden(int minVer, int menge) {

    }



}

Feature: Tests der Amazon-Bestseller-Seite
  Background:
    Given der Benutzer offnet die Startseite
    And der Benutzer lehnt Cookie ab


  @KontrolTest
    Scenario: Überprüfung der Produkte auf der Bestsellerliste
      Given der Benutze klickt auf die Alle
      And der Benutzer klickt auf die Bestseller
      And  der Benutze klickt neben dem Bestseller in Spielzug auf Weitere
      Then der Benutzer klickt auf ein beliebiges Produkt
      When der Benutzer bestätigt, dass mindestens  9.000 Stück des Produkts verkauft wurden.
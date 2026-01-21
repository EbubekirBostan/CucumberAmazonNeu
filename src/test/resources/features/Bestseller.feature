Feature: Tests der Amazon-Bestseller-Seite
  Background:
    Given der Benutzer offnet die Startseite
    And der Benutzer lehnt Cookie ab
    And der Benutzer wahlt Deutsch als Sprache


  @KontrolTest
    Scenario: Überprüfung der Produkte auf der Bestsellerliste
      Given der Benutze klickt auf die Alle
      And der Benutzer klickt auf die Bestseller
      And  der Benutze klickt neben dem Bestseller in Spielzug auf Weitere
      Then der Benutzer klickt auf 20 beliebiges Produkt
      When Der Nutzer bestätigt, dass 50 % der Produkte in der Liste mehr als 500 Mal verkauft wurden.
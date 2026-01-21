Feature: Tests der Amazon-Bestseller-Seite
  Background:
    Given der Benutzer offnet die Startseite
    And der Benutzer wahlt Deutsch als Sprache
    And der Benutzer lehnt Cookie ab

    @kontrol
    Scenario:
      And der Benutzer wahlt Deutsch als Sprache
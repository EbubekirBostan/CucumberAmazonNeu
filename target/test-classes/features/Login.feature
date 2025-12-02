Feature: Amazon Login-Prozesse
  Background:
  Given der Benutzer offnet die Startseite
  And der Benutzer klickt auf die Sign-In-Schaltflache

  @smoke
  @regression
  Scenario: Erfolgreiche Anmeldung eines registrierten Benutzers
    Given der Benutzer gibt die E-Mail "email" ein und klickt auf Weiter
    And der Benutzer gibt das Passwort "passwort" ein und klickt erneut auf Weiter
    Then der Benutzer sollte erfolgreich eingeloggt sein

  @smoke
    @regression
  Scenario Outline: Fehlgeschlagene Anmeldung mit nicht registrierter E-Mail-Adresse
    Given der Benutzer versucht sich mit der E-Mail "<email>" anzumelden
    When der Benutzer die Eingabe bestätigt
    And eine Fehlermeldung sollte angezeigt werden
    Examples:
      | email            |
      | negativeEmail1   |
      | negativeEmail2   |
      | negativeEmail3   |

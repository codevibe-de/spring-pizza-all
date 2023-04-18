# Übungen zum Kapitel "180 - Spring Security"

## a) pom.xml

Fügen Sie die Abhängigkeit für "spring-boot-starter-security" der `pom.xml` hinzu

## b) Neustart - nichts geht mehr

Wenn Sie jetzt die Anwendung neu starten und auf einen Endpunkt zugreifen so werden Sie
überall den Status **401 Unauthorized** erhalten.

Jedoch legt Spring auch in der Default-Konfiguration einen User an, mit dem Sie sich
anmelden können (z.B. mit Basic-Auth). Der Benutzername ist "user" - das Kennwort wird
generiert und im Log ausgegeben.

## c) WhoAmIController

Schauen Sie sich die bereitgestellte Klasse `WhoAmIController` an.

Nun, da Spring-Security im Classpath ist, können Sie die kommentierten Zeilen entkommentieren.

Testen Sie diesen Endpunkt z.B. mit Insomnia. Dort kann Basic-Auth als Authentifizierungsmechanismus
genutzt werden.

Wie könnte das leere Array an Authorities befüllt werden (via Konfiguration der Anwendung)?

## d) eigene Benutzerverwaltung

Implementieren Sie eine eigene Benutzerverwaltung, welche folgende Benutzer kennt:

* einen Administrator (Benutzername "admin", Kennwort "pwd", Authorities "ROLE_ADMIN")
* Kunden (Benutzername ist der Wert von `Customer.phoneNumber`, Kennwort der Wert von `Customer.address.postalCode`,
  Authorities "ROLE_CUSTOMER")

Sie benötigen dafür eine eigene `UserDetailsService` Implementation als Bean. Als Return-Wert bietet
sich eine Instanz der Spring-Security Klasse `User` an.

Denken Sie daran, dass das vom `UserDetailsService` ausgelieferte `UserDetails` Objekt ein gehashtes
Kennwort enthalten muss! Für bcrypt kann hier eines generiert werden: https://bcrypt-generator.com/

Ebenfalls brauchen Sie eine `PasswordEncoder` Bean. Hier bietet sich `BCryptPasswordEncoder` von
Spring an.

Testen Sie den Log-In Ihrer Benutzer und schauen Sie sich die Ausgabe des "/me" Endpunkts hierfür an.

## e) eigene Security-Config

Erstellen Sie eine eigene Security-Configuration, welche:

* den Endpunkt `GET /me` und `GET /orders/greeting` auch ohne Login erlaubt
* den Endpunkt `POST /orders` nur Benutzern mit Rolle "CUSTOMER" erlaubt
* alle anderen Endpunkte nur Benutzern mit Rolle "ADMIN" erlaubt

Benutzer sollen sich mit Basic-Auth anmelden können.

Nun wo wir bei Platzierung der Bestellung einen angemeldeten Kunden haben, was kann dann in den
JSON Bestelldaten weggelassen werden?

## f) Behebung der Testfälle

Beheben Sie die Sicherheitsprobleme in den Testfällen, sodass alle Tests wieder durchlaufen.


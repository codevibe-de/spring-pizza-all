# Übungen zum Kapitel "090 - API Integrationstests"

## a) CustomerRestControllerTest - getCustomer

**Test-Driven-Development (TDD)**: Schreiben Sie einen `CustomerRestControllerTest`, der die
Abfrage (Lesen) eines einzelnen Kunden anhand dessen Id durchführt: "GET /customers/<id>". Diesen
Endpunkt gibt es noch nicht, es ist also Teil dieser Aufgabe, diesen zu implementieren.

Vorgehen:

1. Test schreiben -- da es den Endpunkt und die Service-Logik hierfür noch nicht gibt, wird dieser
   Test vorerst fehlschlagen.
2. Funktionalität rudimentär implementieren
3. Code refakturieren und verbessern, dabei immer Test wieder ausführen als Garantie

Hier führen wir das Konzept einer "PathVariable" ein:
https://www.amitph.com/spring-pathvariable/#reading_path_variable_from_request_uri

Dieser Test soll die JSON Antwort zumindest zu Teilen auf Korrektheit prüfen.

## b) CustomerRestControllerTest - createCustomer

Ergänzen Sie den `CustomerRestControllerTest` um einen Testfall, der die
korrekte Anlage eines neuen Kunden prüft.

Die Prüfung kann nur aus Abfrage des richtigen Statuswerts (201-CREATED) bestehen.

## c) OrderRestControllerTest

Schreiben Sie einen `OrderRestControllerTest` für `placeOrder()`, der das erfolgreiche
Anlegen einer Bestellung inklusive Prüfung auf korrekten Gesamtpreis und Name des
Empfängers validiert, beispielsweise mit folgendem JSON:

````json
{
  "phoneNumber": "123-4567",
  "itemQuantities": {
    "S-02": 1,
    "P-10": 2,
    "P-12": 1
  }
}
````

Versuchen Sie diesen Test nicht als `@SpringBootTest`, sondern mit der Variante `@WebMvcTest`
zu schreiben, sodass nur ein Teil des Kontexts gestartet werden muss (schneller).

Da wir ja einen vollständigen Integrationstest möchten, muss also trotz des Slicings auf
Web-Komponenten hin auch zusätzlich noch die entsprechenden Services und Repositories angelegt
werden.
Dazu brauchen Sie einige andere Annotationen, wie z.B.

* `@Import`
* `@AutoConfigureDataJpa`
* `@EnableJpaRepositories`

Optional kann noch ein Negativtest geschrieben werden, der das Verhalten bei
Angabe einer falschen Kunden-Telefonnummer oder Produkt-Id prüft.

## d) Bestellungen eines Kunden

Diese Aufgabe können Sie "Test Driven" oder ohne Test durchführen.

Erweitern Sie den `CustomerRestController` derart, dass mittels `GET /customers/<id>/orders`
alle Bestellungen eines Kunden ausgeliefert werden.

Dies erfordert zusätzliche Service und Repository Methoden, ist dafür aber auch ein tolles
Feature :)

Wenn wir die Entity `Order` als Rückgabewert nehmen, wird jede Bestellung den kompletten Inhalt
des (immer
gleichen) Kunden enthalten. Das ist unschön. Daher empfiehlt es sich, hier eine dedizierte
Value-Klasse für
die Rückgabewerte zu schreiben. Hierfür eignen sich die neuen Java Records.


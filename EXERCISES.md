# Übungen zu Kapitel "040 Testing"

Hinweis: Aufgabe d) hat den stärksten Spring-bezug, da hier viel mit einem individuellen Kontext
gearbeitet wird.

## a) Test zur Abfrage eines Produkts

**Oops, ein Entwickler hat einen Fehler in der Datenbankschicht gemacht. Finden Sie diesen mittels
eines Integrationstests!**

In diesem Test soll die Abfrage eines Produkts anhand der Methode `ProductService.getProduct()`
getestet werden.

Vorgehen:

1. Erstellen Sie eine `ProductServiceTest` Klasse
2. Injecten Sie eine `ProductRepository` Instanz -- diese brauchen wir für Anlage der benötigten
   Testdaten
3. Erstellen Sie eine Testmethode namens `getProduct()`
4. In der Testmethode legen Sie die Testdaten an:
    1. ein `Product` erzeugen (mittels `new`)
    2. dieses über das `ProductRepository` speichern
5. Dann fragen Sie dieses Produkt über den `ProductService` ab
6. Schlussendlich prüfen Sie mittels Assertions dieses Produkt auf nicht-null und vergleichen
   die erwartete und tatsächliche Produkt-Instanz bzgl. productId, name und price

## b) Test zur Vermeidung doppelter Product-Ids

Ergänzen Sie die Testklasse von oben um einen Test `createProduct__failsForDuplicateId()`,
der sicherstellt, dass bei Anlage zweier Produkte mit der gleichen Product-Id
eine `IllegalStateException` geworfen wird (wenn das zweite Produkt angelegt werden soll).

**Hinweis:** In Assertj gibt es hierfür auch eine spezielle assert-Methode
namens `assertThatThrownBy()`.

## c) Test-Driven-Development von getTotalPrice()

Vervollständigen Sie den Testfall `ProductServiceMockedTest.getTotalPrice()`
sodass mithilfe eines gemockten `ProductRepository` die Geschäftslogik zur
Preisberechnung validiert wird.

Dieser Test wird vorerst fehlschlagen, da die Service-Methode leer ist.

Implementieren Sie dann die Service-Methode (alternativ können Sie sich diesen Code-Block auch
einfach aus dem `solution` Branch ziehen).

**Hinweis:** Bei dieser Aufgabe können Sie mit den berüchtigten Ungenauigkeiten der Floating-Point
Arithmetik konfrontiert
werden (https://en.wikipedia.org/wiki/Floating-point_arithmetic#Accuracy_problems).

Diese treten z.B. dann auf (Bonus-Aufgabe!), wenn Sie 3 Produkte zu 2,10 € und 1 Produkt zu 6,99 €
bestellen.

Wie kann ein Test hiermit umgehen? Schauen Sie sich die zusätzlichen Parameter der `isEqualTo()`
Methode an.
Oder wie kann der Service verbessert werden, um diese Probleme zu umgehen?

## d) CustomerServiceTest mit individuellem Kontext

Ziel des Tests ist es, dass ein durch den `DataLoader` angelegter Kunde über den
`CustomerService` gefunden wird (z.B. Abfrage via Mobilnummer).

Der Kontext soll hierbei nur aus den Beans bestehen, die wir wirklich brauchen. Welche sind
das?

* natürlich der `CustomerService`
* und alles, was wir für das Laden der Sample-Daten benötigen
    * eine `DataLoader` Instanz braucht ja leider auch eine Referenz auf einen `ProductService`,
      daher braucht es hierfür eine Lösung
    * Was bietet sich hier an? Tipp: Produktdaten sind uns völlig egal, der Service kann machen,
      was er will (bzw. gar nichts machen)

In dieser Übung nutzen wir die Annotation `@TestConfiguration`, um den Kontext per Hand aufzubauen.

D.h. die `CustomerServiceTest` Klasse benötigt nur eine `@ExtendWith({SpringExtension.class})`
Annotation, **nicht** `@SpringBootTest`.

Zum Aufbau des Kontexts brauchen wir eine Spring-Configuration Klasse, die

* mit `@TestConfiguration` annotiert ist
* vom Test importiert wird bzw. als innere statische Klasse automatisch genutzt wird
* mittels Annotationen die benötigten Beans lädt

Irgendwie müssen Sie dann auch noch den `DataLoadRunner` starten, denn dies passiert
nur dann automatisch, wenn man `@SpringBootTest` nutzt!
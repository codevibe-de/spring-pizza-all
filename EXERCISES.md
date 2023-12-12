# Übungen zu Kapitel "040 Testing"

Hinweis: Aufgabe d) hat den stärksten Spring-bezug, da hier viel mit einem individuellen Kontext
gearbeitet wird.

## a) Test zur Abfrage eines Produkts

Oops, ein Entwickler hat einen Fehler in der Datenbankschicht gemacht. Finden Sie diesen mittels
eines Integrationstests!

Dazu erstellen Sie eine `ProductServiceTest` Klasse mit der Testmethode `getProduct()`.

In diesem Test soll die Abfrage eines Produkts anhand der Methode `ProductService.getProduct()`
getestet werden.

Der Test soll mittels einer `ProductRepository` Instanz die benötigten Testdaten selbst anlegen.

Also z.B. folgende Reihenfolge:

1. Ein `Product` erzeugen (mittels `new`)
2. Dieses über das `ProductRepository` speichern
3. Mittels der Id des Produkts dieses über den `ProductService` abfragen
4. Mittels Assertions auf null prüfen und beide Instanzen vergleichen (productId, name, price)

## b) Test zur Vermeidung doppelter Product-Ids

Ergänzen Sie die Testklasse von oben um einen Test `createProduct__failsForDuplicateId()`,
der sicherstellt, dass bei Anlage zweier Produkte mit der gleichen Product-Id
eine `IllegalStateException` geworfen wird (wenn das zweite Produkt angelegt werden soll).

**Hinweis:** In Assertj gibt es hierfür auch eine spezielle assert-Methode
namens `assertThatThrownBy()`.

## c) Test-Driven-Development von getTotalPrice()

Vervollständigen Sie den Testfall `ProductServiceMockedTest.getTotalPrice()`
sodass dieser mithilfe eines gemockten `ProductRepository` die Geschäftslogik zur
Preisberechnung validiert wird.

Dieser Test wird vorerst fehlschlagen, da diese Service-Methode leer ist.

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
`CustomerService` geladen werden soll (z.B. Abfrage via Mobilnummer).

In dieser Übung nutzen wir die Annotation `@TestConfiguration`, um den Kontext per Hand aufzubauen.

D.h. die `CustomerServiceTest` Klasse benötigt nur eine `@ExtendWith({SpringExtension.class})`
Annotation, NICHT `@SpringBootTest`.

Der Kontext soll entsprechend nur aus den Klassen bestehen, die wir wirklich brauchen. Welche sind
das?

* natürlich der `CustomerService`
* und alles, was für das Laden der Sample-Daten benötigen
* da ja leider dieser auch eine Referenz auf den `ProductService` benötigt, braucht es hierfür eine
  Lösung. Was bietet sich hier an? Tipp: Produktdaten sind uns völlig egal, der Service kann machen,
  was er will (bzw. gar nichts machen)

D.h. Sie brauchen eine neue Klasse, die

* mit `@TestConfiguration` annotiert ist
* vom Test importiert wird bzw. als innere statische Klasse automatisch genutzt wird
* mittels Annotationen die benötigten Beans laden

Irgendwie müssen Sie dann auch noch den `DataLoadRunner` starten, denn dies passiert
nur dann automatisch, wenn man `@SpringBootTest` nutzt!
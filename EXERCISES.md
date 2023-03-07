# ÜBUNGEN SPRING BOOT

## 040 Testing

### a) Test zur Abfrage eines Produkts

Oops, ein Entwickler hat einen Fehler in der Datenbankschicht
gemacht. Finden Sie diesen mittels eines Integrationstests!

Dazu erstellen Sie eine `ProductServiceTest` Klasse mit der Testmethode
`getProduct()`.

In diesem Test soll die Abfrage eines Produkts anhand der Methode `ProductService.getProduct()`
getestet werden.

Der Test soll mittels einer `ProductRepository` Instanz
die benötigten Testdaten selbst anlegen.

Also z.B. folgende Reihenfolge:

1. Ein `Product` erzeugen (mittels `new`)
2. Dieses über das `ProductRepository` speichern
3. Mittels der Id des Produkts dieses über den `ProductService` abfragen
4. Mittels Assertions auf null prüfen und beide Instanzen vergleichen (productId, name, price)

### b) Test zur Vermeidung doppelter Product-Ids

Ergänzen Sie die Testklasse von oben um einen Test `createProduct__failsForDuplicateId()`,
der sicherstellt, dass bei Anlage
zweier Produkte mit der gleichen Product-Id eine `IllegalStateException` geworfen wird (wenn
das zweite Produkt angelegt werden soll).

**Hinweis:** In Assertj gibt es hierfür auch eine spezielle assert-Methode namens `assertThatThrownBy()`.

### c) Test-Driven-Development von getTotalPrice()

Vervollständigen Sie den Testfall `ProductServiceMockedTest.getTotalPrice()`
sodass dieser mithilfe eines gemockten `ProductRepository` die Geschäftslogik zur
Preisberechnung validiert wird.

Dieser Test wird vorerst fehlschlagen, da diese Service-Methode leer ist.

Implementieren Sie dann die Service-Methode.

**Hinweis:** Bei dieser Aufgabe können Sie mit den berüchtigten Ungenauigkeiten der Floating-Point Arithmetik
konfrontiert werden (https://en.wikipedia.org/wiki/Floating-point_arithmetic#Accuracy_problems).

Diese treten z.B. dann auf (Bonus-Aufgabe!), wenn Sie 3 Produkte zu 2,10 € und 1 Produkt zu 6,99 € bestellen.

Wie kann ein Test hiermit umgehen? Schauen Sie sich die zusätzlichen Parameter der `isEqualTo()` Methode an.
Oder wie kann der Service verbessert werden, um diese Probleme zu umgehen?

### d) Abfrage eines Kunden

In dieser Übung nutzen wir die Annotation `@TestConfiguration`, um den Kontext per Hand aufzubauen.

D.h. die Testklasse benötigt nur eine `@ExtendWith({SpringExtension.class})` Annotation, NICHT
`@SpringBootTest`.

Ziel des Tests ist es, dass ein durch den `SampleDataLoader` angelegte Kunde über den
`CustomerService` geladen werden soll (z.B. Abfrage via Mobilnummer).

Der Kontext soll:

* nur die Beans aus dem Package `product` und `customer` enthalten (Tipp: `@ComponentScan` nutzen)
* außerdem soll das `ProductRepository` durch die bereitgestellte `NoOpProductRepository` Klasse ersetzt werden

D.h. Sie brauchen eine neue Klasse, die

* mit `@TestConfiguration` annotiert ist
* vom Test importiert wird bzw. als innere Klasse automatisch genutzt wird
* den Component-Scan deklariert
* zusätzlich den `SampleDataLoader` und `SampleDataLoaderRunner` importiert
* die `ProductRepository` Bean austauscht

Irgendwie müssen Sie dann auch noch den Runner starten, denn dies passiert nicht automatisch
wenn man nicht `@SpringBootTest` nutzt.
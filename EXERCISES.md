# ÜBUNGEN SPRING BOOT

## 040 Testing

### a) Test zur Abfrage eines Produkts

Erstellen Sie eine `ProductServiceTest` Klasse mit der Testmethode
`getProduct()`.

In diesem Test soll die Abfrage eines Produkts anhand der Methode `ProductService.getProduct()`
getestet werden.

Der Test soll mittels einer `ProductRepository` Instanz
die benötigten Testdaten selbst anlegen.

### b) Test zur Vermeidung doppelter Product-Ids

Ergänzen Sie die Testklasse von oben um einen Test, der sicherstellt, dass bei Anlage
zweier Produkte mit der gleichen Product-Id eine `IllegalStateException` geworfen wird (wenn
das zweite Produkt angelegt werden soll).

**Hinweis:** In Assertj gibt es hierfür auch eine spezielle asert-Methode namens `assertThatThrownBy()`.

### c) Test-Driven-Development von getTotalPrice()

Vervollständigen Sie den Testfall `ProductServiceTest_WithMocks.getTotalPrice()`
sodass dieser mithilfe eines gemockten `ProductRepository` die Geschäftslogik zur
Preisberechnung validiert wird.

Dieser Test wird vorerst fehlschlagen, da diese Service-Methode leer ist.

Implementieren Sie dann die Service-Methode.
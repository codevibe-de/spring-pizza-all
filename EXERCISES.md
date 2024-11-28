# Übungen zum Kapitel "080 - RESTful API"

## a) Clients

### Insomnia

Bei Bedarf können Sie den "Insomnia" Rest Client installieren: https://insomnia.rest/

Im Projekt ist ab diesem Branch ein exportierter Insomnia Workspace vorhanden, den Sie in das Tool
importieren können. Damit können Sie dann komfortabel die später programmierten Endpunkte anfragen.

### Intellij

Ebenfalls findet sich nun die Datei `requests.http` im Projektwurzelverzeichnis. Das können Sie in
Intellij **Ultimate** öffnen und von dort die Requests direkt mit dem grünen Pfeil ausführen lassen.

### Curl

Das Kommandozeilen-Tool `curl` kann wie folgt genutzt werden:

- einfaches GET auf eine Adresse:
  ````shell
  curl http://localhost:8080/products
  ````
- POST mit Body, dessen Inhalt aus einer Datei gelesen werden soll:
  ````shell
  curl -d @<filename> http://localhost:8080/orders
  ````
- DELETE mit Query-String:
  ````shell
  curl -X DELETE http://localhost:8080/products?namePrefix=Pizza
  ````

### Sonstige Clients

Alternativ können Sie zum Testen der Endpunkte natürlich auch andere Tools wie z.B. Postman nehmen.

## b) Spring Starter

Fügen Sie die Maven Dependency `spring-boot-starter-web` zum Build hinzu.

## c) OrderRestController

Erstellen Sie einen API-Endpunkt `/orders/greeting` der für ein GET eine Begrüßung als String
zurückgibt.

Testen Sie die URL in Ihrem Browser: http://localhost:8080/orders/greeting

## d) CustomerRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /customers`, der alle Kunden zurückgibt
* `POST /customers`, der einen neuen Kunden anlegt

## e) ProductRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /products`, der alle Produkte zurückgibt
* `GET /product/{id}`, der das Produkt mit der angegebenen Id zurückgibt (Nutzung `@PathVariable`)

## f) Erweiterung OrderRestController

Erstellen Sie zusätzlich die folgenden API-Endpunkte:

* `GET /orders`, der alle Bestellungen zurückgibt
* `POST /orders`, durch den eine neue Bestellung aufgegeben werden kann. Die Bestellung ist durch
  folgenden JSON Inhalt definiert:

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

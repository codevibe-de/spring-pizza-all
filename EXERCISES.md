# Übungen zum Kapitel "080 - RESTful API"

## a) Insomnia Rest Client

Bei Bedarf können Sie den "Insomnia Core" Rest Client installieren: https://insomnia.rest/

Im Projekt ist ab diesem Branch ein exportierter Insomnia Workspace vorhanden, den Sie in das Tool importieren können.
Oder Sie legen die wenigen Requests selbst an.

Alternativ können Sie zum Testen der Endpunkte natürlich auch andere Tools wie z.B. Postman oder den
Kommandozeilenbefehl `curl` nehmen.

## b) OrderRestController

Erstellen Sie einen API-Endpunkt `/orders/greeting` der für ein GET
eine Begrüßung als String zurückgibt.

Testen Sie die URL in Ihrem Browser: http://localhost:8080/orders/greeting

## c) CustomerRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /customers`, der alle Kunden zurückgibt
* `POST /customers`, der einen neuen Kunden anlegt

## d) ProductRestController

Erstellen Sie die folgenden API-Endpunkte:

* `GET /products`, der alle Produkte zurückgibt

## e) Erweiterung OrderRestController

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

## f) Sub-Ressource Bestellungen eines Kunden

Erweitern Sie den `CustomerRestController` derart, dass mittels `GET /customers/<id>/orders`
alle Bestellungen eines Kunden ausgeliefert werden.

Hierdurch führen wir u.a. das Konzept einer "PathVariable" ein:
https://www.amitph.com/spring-pathvariable/#reading_path_variable_from_request_uri

Dies erfordert zusätzliche Service und Repository Methoden, ist dafür aber auch ein tolles Feature :)

Wenn wir die Entity `Order` als Rückgabewert nehmen, wird jede Bestellung den kompletten Inhalt des (immer
gleichen) Kunden enthalten. Das ist unschön. Daher empfiehlt es sich, hier eine dedizierte Value-Klasse für
die Rückgabewerte zu schreiben. Hierfür eignen sich die neuen Java Records.
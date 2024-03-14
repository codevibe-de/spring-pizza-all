# Übungen zu 005

Schauen Sie sich die Klasse `PizzaApp` an. Diese ist noch unvollständig, führt allerdings schon
einige Operationen aus und gibt deren Ergebnis auf `System.out` aus:

* Abfrage und Ausgabe des Produkts mit Id "P-10"
* Abfrage und Ausgabe des Kunden mit Mobilnummer "+49 123 456789"
* Platzierung einer Bestellung, konkret 3-mal die Pizza "P-10" und einmal den Salat "S-03"

Die Anwendung kann über die `main()` Methode gestartet werden, erzeugt dann aber noch Fehler.

Bei einem Blick in die Klasse ist der Grund dafür schnell klar -- es fehlen noch die
Instanziierungen.

**Ergänzen Sie die fehlenden Teile, sodass die Anwendung fehlerfrei läuft und Ausgaben erzeugt.**

Was macht wohl diese Code-Zeile?

````java
new DataLoader.Sample(productService, customerService).

run();
````

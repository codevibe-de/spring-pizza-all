# Übungen zum Kapitel "060 - Konfiguration"

## a) OrderService konfigurierbar machen

Verändern Sie den `OrderService`, sodass die dort definierten Eigenschaften mittels Konfiguration
gesetzt werden können:

* erwartete Lieferzeit in Minuten
* die Wochentage, an denen der Rabatt gilt 
* der Rabattsatz (in %)

Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`).
Bei letzterem Ansatz brauchen Sie die Felder der Klasse nicht mehr, da Sie ja dann diese über
eine neue `OrderProperties` Klasse injectet bekommen. 

Über die Testklasse `OrderServiceTest` können Sie probeweise einen Bestellvorgang auslösen.

In der nächsten Übung b) werden die Werte in der `application.properties` Datei gesetzt und
probeweise ausgegeben.

## b) Ausgabe der Konfiguration

Ergänzen Sie den `OrderService` um eine Methode, in der die Konfiguration via `System.out`
ausgegeben wird (damit wir sehen können, was gerade gilt).

Lassen Sie Spring diese Methode automatisch beim Start ausführen. Wie ging das nochmal ...? :)

Setzen Sie Werte für die Konfiguration des `OrderService` in der `application.properties` Datei.

Starten Sie nun die Anwendung und prüfen Sie die tatsächlich vorliegende Konfiguration --
wird der Wert aus der `application.properties` genutzt?

## c) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass nicht die Lieferzeit in Minuten
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert
überschrieben wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

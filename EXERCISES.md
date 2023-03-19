# Übungen zum Kapitel "060 - Konfiguration"

## a) OrderService konfigurierbar machen

Verändern Sie den `OrderService`, sodass die erwartete Lieferzeit in Minuten sowie die
Rabattierung je Wochentag in einer externen Konfigurationsquelle hinterlegt werden kann.

*Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`,
letztere ist schwerer aufgrund des noch zu implementierenden Converters, siehe Musterlösung)

Über die Testklasse `OrderServiceTest` können Sie testhalber einen Bestellvorgang auslösen.

## b) Ausgabe der Konfiguration

Ergänzen Sie den `OrderService` um eine Methode, in der die Konfiguration via `System.out`
ausgegeben wird (damit wir sehen können, was gerade gilt).

Lassen Sie Spring diese Methode automatisch beim Start ausführen. Wie ging das nochmal...? :)

## c) Konfiguration via application.properties

Setzen Sie Werte für die Konfiguration der vorherigen Seite in der `application.properties`.

Starten Sie nun die Anwendung und prüfen Sie die tatsächlich vorliegende Konfiguration --
wird der Wert aus der `application.properties` genutzt?

## d) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass nicht die Lieferzeit in Minuten
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert
überschrieben wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

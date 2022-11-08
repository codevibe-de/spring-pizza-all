# ÜBUNGEN SPRING BOOT

## 060 - Konfiguration

### a) placeOrder Test

Erstellen Sie einen einfachen `placeOrder()` Test in der Klasse `OrderServiceTest`, der einfach
nur eine Bestellung auslöst -- ohne irgendwelche Assertion-Checks.

Diesen Test nutzen wir rein dafür, um die Geschäftslogik einer Bestellung einfach ausführbar zu machen.

### b) OrderService konfigurierbar machen

Verändern Sie den `OrderService`, sodass die erwartete Lieferzeit in Minuten sowie die
Rabattierung je Wochentag in einer externen Konfigurationsquelle hinterlegt werden kann.

*Hinweis: Hierfür gibt es zwei Möglichkeiten (mittels `@Value` oder `@ConfigurationProperties`,
letztere ist schwerer aufgrund des noch zu implementierenden Converters, siehe Musterlösung)

### c) Konfiguration via application.properties

Setzen Sie Werte für die Konfiguration der vorherigen Seite in der `application.properties`.

Geben Sie die aktuelle Konfiguration in der `OrderService.placeOrder()` Methode aus (via `System.out`).

Starten Sie die Anwendung und prüfen Sie die tatsächlich vorliegende Konfiguration --
wird der Wert aus der `application.properties` genutzt?

### d) Konfiguration von außen

Starten Sie Ihre Anwendung auf eine Art und Weise, dass die Lieferzeit in Minuten nicht
aus den `application.properties` genutzt wird, sondern von außen durch einen anderen Wert
überschrieben wird.

Hierfür können Sie eine Umgebungsvariable, ein VM System Property oder ein Programmargument nutzen.

Wird der erwartete Wert ausgegeben?

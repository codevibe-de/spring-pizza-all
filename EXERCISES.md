# Übungen zum Kapitel "050 Spring-Data JPA"

In dieser Übung migrieren Sie die bisher ausprogrammierte Persistenz von Produkten
auf Spring-Data JPA -- und können auch für andere Fachlichkeiten eine Datenbank-Persistenz einführen.

Denken Sie bei dieser Übung daran, dass wir schon Testfälle haben, mit denen wir
Test-Driven arbeiten können, d.h. Sie können diese Testfälle immer wieder ausführen, um den Fortschritt Ihrer
Arbeit zu prüfen.

## a) Build Files anpassen

Fügen Sie den entsprechenden Starter für "Data JPA" Ihrer `pom.xml` bzw. `build.gradle` Datei hinzu.

Welcher Starter wird nun wohl nicht mehr benötigt und kann weg bzw. ersetzt werden?

## b) Migration `JdbcProductRepository`

Die Klasse `JdbcProductRepository` wird mit Spring-Data nicht mehr benötigt.

Entfernen Sie diese und schreiben Sie das `ProductRepository` so um, dass es von Spring erkannt wird
und als Ersatz für das gelöschte `JdbcProductRepository` fungiert.

Ebenfalls kann die Klasse `HashMapProductRepository` nun entfernt werden, da wir immer mit der
Datenbank arbeiten.

## c) JPA Entity `Product`

Die Klasse `Product` muss noch zur vollwertigen JPA Entity mit Annotationen ergänzt werden.

Denken Sie daran, dass unser Schema (Namen von Tabelle und Feldern) zu berücksichtigen ist!

## d) Bonus: Persistenz für Bestellungen

Momentan arbeitet der `OrderService` noch mit einer in der Klasse verwalteten `List` an
Bestellungen (quasi in-memory).

Stellen Sie dies auf eine Spring-Data basierte Persistenz um.

ACHTUNG -- unbedingt darauf achten, wie das Schema definiert ist und dies in den
Annotationen beachten. Für das ManyToOne mapping muss auch noch der Name der JOIN
Column definiert werden (`@JoinColumn`),
siehe https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne#Example_of_a_ManyToOne_relationship_annotations

## e) Bonus: Persistenz für Kunden

Ebenfalls arbeitet der `CustomerService` noch mit einer in der Klasse verwalteten `List` an Kunden.

Stellen Sie dies auf eine Spring-Data basierte Persistenz um.

Dies führt zu zwei Problemen im `CustomerServiceTest`: einerseits muss auf `@DataJpaTest` umgestellt
werden, und andererseits muss dann der `DataLoadRunner` nicht mehr manuell gestartet werden, da
dies dann automatisch passiert (und mit dem manuellen Start doppelt!)

ACHTUNG, außerdem fehlen noch Spaltendefinitionen in der `schema.sql` Datei -- siehe `TODO`
Kommentar dort. Insbesondere die Einbettung der `Address` in die Tabelle ist eine fortgeschrittene
Übung. Folgende Seite kann hierbei helfen: https://en.wikibooks.org/wiki/Java_Persistence/Embeddables
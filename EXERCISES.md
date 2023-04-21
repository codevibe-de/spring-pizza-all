# Übungen zum Kapitel "050 Spring-Data JPA"

In dieser Übung migrieren Sie die bisher `JdbcTemplate` basierte Persistenz
auf Spring-Data JPA.

Denken Sie bei dieser Übung daran, dass wir schon Testfälle haben, mit denen wir
Test-Driven arbeiten können, d.h. immer wieder diese ausführen, um den Fortschritt Ihrer
Arbeit zu prüfen.

## a) pom.xml

Fügen Sie den entsprechenden Starter für "Data JPA" Ihrer `pom.xml` hinzu.

Welcher Starter wird nun wohl nicht mehr benötigt und kann weg bzw. ersetzt werden?

## b) Umstellung auf in-memory H2

Leider ist die Nutzung unseres bisherigen H2 Servers, der Verbindungen über TCP
annimmt, mit Spring Boot nicht einfach so weiter nutzbar.

Daher steigen wir um auf eine In-Memory H2 Datenbank, die von Spring Boot
auto-konfiguriert wird. Dies erfolgt am einfachsten, wenn alle `spring.datasource.*`
Properties entfernt werden.

Fügen Sie aber unbedingt noch das notwendige Property ein, um Hibernate an der automatischen
Generierung des Datenbank-Schemas zu hindern. Wir wollen ja (wie im echten
Produktionsbetrieb) mit einem fertigen Schema arbeiten, welches in der `schema.sql`
definiert ist.

Nun können auch die `H2Launcher` und `H2ScriptRunner` Klassen weg. Warum?

## c) Migration `ProductJdbcDao`

Die Klasse `ProductJdbcDao` wird mit Spring-Data nicht mehr benötigt.

Entfernen Sie diese und schreiben Sie das `ProductRepository` so um, dass es von Spring erkannt wird
und als Ersatz für das gelöschte `ProductJdbcDao` fungiert.

## d) JPA Entity `Product`

Die Klasse `Product` muss noch zur vollwertigen JPA Entity mit Annotationen ergänzt werden.

Denken Sie daran, dass unser Schema (Namen von Tabelle und Feldern) zu berücksichtigen ist!

## e) Bonus: Persistenz für Bestellungen

Momentan arbeitet der `OrderService` noch mit einer in der Klasse verwalteten `List` an
Bestellungen (quasi in-memory).

Stellen Sie dies auf eine Spring-Data basierte Persistenz um.

ACHTUNG -- unbedingt darauf achten, wie das Schema definiert ist und dies in den
Annotationen beachten. Für das ManyToOne mapping muss auch noch der Name der JOIN
Column definiert werden (`@JoinColumn`),
siehe https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne#Example_of_a_ManyToOne_relationship_annotations

## f) Bonus: Persistenz für Kunden

Ebenfalls arbeitet der `CustomerService` noch mit einer in der Klasse verwalteten `List` an Kunden.

Stellen Sie dies auf eine Spring-Data basierte Persistenz um.

Dies führt zu zwei Problemen im `CustomerServiceTest`: einerseits muss auf @DataJpaTest umgestellt
werden, und andererseits muss dann der sample-data runner nicht mehr manuell gestartet werden, da
dies dann automatisch passiert (und mit dem manuellen Start doppelt!)

ACHTUNG, außerdem fehlen noch Spaltendefinitionen in der `schema.sql` Datei -- siehe `TODO`
Kommentar dort. Insbesondere die Einbettung der `Address` in die Tabelle ist eine fortgeschrittene
Übung. Folgende Seite kann hierbei
helfen: https://en.wikibooks.org/wiki/Java_Persistence/Embeddables
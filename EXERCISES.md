# ÜBUNGEN SPRING BOOT

## 050 Spring-Data JPA

In dieser Übung migrieren Sie die bisher `JdbcTemplate` basierte Persistenz
auf Spring-Data JPA.

Denken Sie bei dieser Übung daran, dass wir schon Testfälle haben, mit denen wir
Test-Driven arbeiten können, d.h. immer wider diese ausführen, um den Fortschritt Ihrer
Arbeit zu prüfen.

### a) pom.xml

Fügen Sie den entsprechenden Starter in Ihrer `pom.xml` hinzu.

Welcher Starter wird nun wohl nicht mehr benötigt und kann weg bzw. ersetzt werden?

### b) Umstellung auf in-memory H2

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

### c) Migration `ProductJdbcDao`

Die Klasse `ProductJdbcDao` wird mit Spring-Data nicht mehr benötigt.

Entfernen Sie diese und schreiben Sie das `ProductRepository` so um, dass es von Spring erkannt wird
und als Ersatz für das gelöschte `ProductJdbcDao` fungiert.

### d) JPA Entity `Product`

Die Klasse `Product` muss noch zur vollwertigen JPA Entity mit Annotationen ergänzt werden.

Denken Sie daran, dass unser Schema (Namen von Tabelle und Feldern) zu berücksichtigen ist!

### e) Bonus: Persistenz für Bestellungen

Momentan arbeitet der `OrderService` ja noch ganz simpel mit einer selbst verwalteten Liste an Bestellungen.

Stellen Sie dies auf eine Spring-Data basierte Persistenz um.
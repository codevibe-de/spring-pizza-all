# Übungen zu 025 "Beans"

Machen Sie aus der bestehenden Kommandozeilenanwendung eine Spring Boot basierte Anwendung.

Die bestehende Klasse `PizzaApp` ist hierfür der Ausgangspunkt. Diese wurde auf Basis einer bereits angepassten
`pom.xml` bzw. `build.gradle` Datei bereits für Spring vorbereitet.

Diese Übung besteht aus **zwei Phasen**:

## Phase 1

Wir wollen, dass Spring nun alle Beans erzeugt und verwaltet.

D.h. das Erzeugen der Instanzen überlassen wir ab jetzt Spring -- und machen dies nicht mehr
mittels `new` selbst.

Das heißt aber auch, dass Sie Spring darüber "informieren" müssen, welche Beans anzulegen sind.
Dafür haben wir die notwendigen Annotationen kennengelernt.

Gerne können Sie statt der komplexeren `JdbcProductRepository` Klasse die einfache `HashMapProductRepository` Variante
nehmen.

### Bonus: Nutzung Datenbank

Wer die Datenbank-Variante des `ProductRepository` nutzen möchte, braucht hierfür eine laufende
Datenbank und eine `DataSource` Bean. Diese legt Spring Boot für uns automatisch an, da wir die H2 Datenbank
im Classpath haben (Autoconfiguration).

Somit können eine ganze Reihe an eigenen Klassen entfernt werden:

* `H2TcpServer.java` -- denn Spring startet automatisch eine interne H2 Datenbank
* `SchemaScriptRunner.java` -- denn Spring führt automatisch eine vorhandene `schema.sql` Datei aus
* `PersistenceException.java` -- das Spring JDBC Projekt bringt eine Reihe an Exceptions mit

Die Nutzung des `JdbcProductRepository` ist dann gar nicht mehr so schwer.

## Phase 2

Wie arbeiten wir nun mit den jetzt vorhandenen Service-Beans?

Sprich, an welcher Stelle erfolgt nun das Ausführen der Geschäftslogik, die bisher in der `PizzaApp.main()` Methode
gestanden hat?

Sie können eine der folgenden zwei Optionen nutzen:

1. eine `LogicRunner` Klasse anlegen (implementiert `ApplicationRunner`)
   - zur Spring Bean deklarieren
   - Geschäftslogik hierhin kopieren
   - notwendige Beans autowiren
2. oder Sie nutzen den von `SpringApplication.run()` zurückgegebenen Kontext und holen sich von dort
   die notwendigen Beans 


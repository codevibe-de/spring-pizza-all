# Übungen zu 025 "Beans"

Machen Sie aus der bestehenden Kommandozeilenanwendung eine Spring Boot basierte Anwendung.

Die bestehende Klasse `PizzaApp` ist hierfür der Ausgangspunkt. Diese wurde mittels einer neuen
`pom.xml` bzw. `build.gradle` bereits für Spring vorbereitet.

Diese Übung besteht im Grunde aus zwei weiteren Phasen:

## Phase 1

Wir wollen, dass Spring nun alle Beans erzeugt und verwaltet.

D.h. das Erzeugen der Instanzen überlassen wir ab jetzt Spring -- und machen dies nicht mehr
mittels `new` selbst.

Das heißt aber auch, dass Sie Spring darüber "informieren" müssen, welche Beans anzulegen sind.
Dafür haben wir die notwendigen Annotationen kennengelernt.

### Bonus: Nutzung Datenbank

Wer die Datenbank-Variante des `ProductRepository` nzutzen möchte, braucht hierfür eine laufende
Datenbank und eine `DataSource` Bean. Diese legt Spring Boot für uns automatisch an, da wir die H2 Datenbank
im Classpath haben (Autoconfiguration).

TODO:

* eine `H2TcpServer` Bean (damit die Datenbank überhaupt läuft) und der automatische Aufruf dessen `start()` Methode
* den `SchemaScriptRunner` (damit das Datenbank-Schema vorhanden ist) und Aufruf dessen `run()` Methode
* eine `SampleDataLoader` Instanz (damit Daten in der Datenbank existieren) und Aufruf dessen `run()` Methode (am besten
  auch über einen Runner)

Achtung -- die Reihenfolge der Runner ist entscheidend (Script vor SampleData). Hierfür die Annotation `@Order` nutzen.

## Phase 2

Wie arbeiten wir dann mit den nun vorhandenen Service-Beans?

Sprich, an welcher Stelle erfolgt nun das Ausführen der Service-Calls, die bisher in der `PizzaApp.main()` Methode
gestanden haben?

Sie können folgende Optionen nutzen:

* eine `ApplicationRunner` Klasse programmieren und zur Spring Bean deklarieren, darein lassen
  Sie sich die Beans injecten, die Sie nutzen möchten
* oder Sie nutzen den von `SpringApplication.run()` zurückgegebenen Kontext und holen sich dort
  Beans heraus und rufen diese auf


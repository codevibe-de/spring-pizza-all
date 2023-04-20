# Übungen zu 025 "Beans"

## Aufgabe

Machen Sie aus der bestehenden Kommandozeilenanwendung eine Spring Boot basierte Anwendung.

Die bestehende Klasse `PizzaApp` ist hierfür der Ausgangspunkt.

Diese Übung besteht im Grunde aus drei Phasen:

### Phase 1

Wir brauchen eine neue `pom.xml`, damit aus der bestehenden Anwendung eine Spring-Boot Anwendung wird.

Diese kann über den Spring Initializr
generiert werden und in das Projekt reinkopiert werden.

WICHTIG, Sie benötigen die Dependencies

* `spring-boot-starter-jdbc` (im Initializr "JDBC API" genannt)
* und H2 (kann auch über den Initializr hinzugefügt werden oder nachträglich aus der alten POM)

### Phase 2

Wir wollen, dass Spring nun Beans erzeugt und verwaltet.

D.h. das Erzeugen der Instanzen überlassen wir ab jetzt Spring -- und machen dies nicht mehr
mittels `new` selbst.

Das heißt aber auch, dass Sie Spring darüber "informieren" müssen, welche Beans anzulegen sind.

Übrigens: Die `DataSource` Instanz legt Spring Boot für uns automatisch an, da wir die H2 Datenbank
im Classpath haben (Autoconfiguration). Jedoch müssen wir konfigurieren, welche Datenbank-URL dafür
zu nutzen ist (da wir ja den H2 Server als Standalone-Prozess selber starten und nicht den
internen Boot-Default Wert nehmen). Das geht in den `application-properties`:

````properties
spring.datasource.url=jdbc:h2:tcp://localhost:9092/~/training.spring-boot.pizza
spring.datasource.driverClassName=org.h2.Driver
````

Was wir immer noch brauchen sind:

* den `H2Launcher` (damit die Datenbank überhaupt läuft) und Aufruf dessen `start()` Methode
* den `H2ScriptRunner` (damit das Datenbank-Schema vorhanden ist) und Aufruf dessen `run()` Methode
* eine `SampleDataLoader` Instanz (damit Daten in der Datenbank existieren) und Aufruf dessen `run()` Methode

### Phase 3

Wie arbeiten wir dann mit den nun vorhandenen Service-Beans?

Sprich, an welcher Stelle erfolgt nun das Ausführen der Service-Calls, die bisher in der `PizzaApp.main()` Methode
gestanden haben?

Sie können folgende Optionen nutzen:

* eine `ApplicationRunner` Klasse programmieren und zur Spring Bean deklarieren, darein lassen
  Sie sich die Beans injecten, die Sie nutzen möchten (z.B. den `CustomerService`)
* oder Sie nutzen den von `SpringApplication.run()` zurückgegebenen Kontext und holen sich dort
  Beans heraus und rufen diese auf

## Troubleshooting

* Es gibt keine `DataSource` Bean -- dies liegt vermutlich daran, dass Sie
  den `spring-boot-starter-jdbc` nicht in der POM haben
* Database Connection Timeout -- der `H2Launcher` muss vor dem `H2ScriptRunner` ausgeführt werden.
  Hier hilft die `@DependsOn` Annotation
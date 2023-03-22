# Übungen zu 025

Machen Sie aus der bestehenden Kommandozeilenanwendung eine Spring Boot basierte Anwendung.

Die bestehende Klasse `App` ist hierfür der Ausgangspunkt.

Und wir brauchen natürlich auch eine neue `pom.xml`. Diese kann über den Spring Initializr
generiert werden und in das Projekt reinkopiert werden.

Das Erzeugen der Instanzen überlassen wir ab jetzt natürlich Spring -- und machen dies nicht mehr
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

Wie arbeiten wir dann mit den Beans? Sie können folgende Optionen nutzen:

* eine `ApplicationRunner` Instanz programmieren und zur Spring Bean deklarieren, darein lassen
  Sie sich die Beans injecten, die Sie nutzen möchten (z.B. den `CustomerService`)
* oder Sie nutzen den von `SpringApplication.run()` zurückgegebenen Kontext und holen sich dort
  Beans heraus und rufen diese auf

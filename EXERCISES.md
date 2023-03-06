# Übungen zu 025

Machen Sie aus der bestehenden Kommandozeilenanwendung eine Spring Boot basierte Anwendung.

Die bestehende Klasse `App` ist hierfür der Ausgangspunkt.

Und wir brauchen natürlich auch eine neue `pom.xml`. Diese kann über den Spring Initializr
generiert werden und in das Projekt reinkopiert werden.

Das Erzeugen der Instanzen überlassen wir ab jetzt natürlich Spring -- und machen dies nicht mehr
mittels `new` selbst.

Das heißt aber auch, dass Sie Spring darüber "informieren" müssen, welche Beans anzulegen sind.

Wie arbeiten wir dann mit den Beans? Sie können folgende Optionen nutzen:

* eine `ApplicationRunner` Instanz programmieren und zur Spring Bean deklarieren, darein lassen
  Sie sich die Beans injecten, die Sie nutzen möchten (z.B. den `CustomerService`)
* oder Sie nutzen den von `SpringApplication.run()` zurückgegebenen Kontext und holen sich dort
  Beans heraus und rufen diese auf

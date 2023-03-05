# Übung zu 025

Machen Sie aus der bestehenden Kommandozeilenanwendung eine Spring Boot basierte Anwendung.

Die bestehende Klasse `App` ist hierfür der Ausgangspunkt. Dort muss die entsprechende Spring Boot
Annotation hin. 

Das Erzeugen der Instanzen überlassen wir ab jetzt natürlich Spring, d.h. der Code im Konstruktor
ist überflüssig.

An die entsprechenden Klassen müssen dann Annotation hinzugefügt werden, damit Spring Beans erstellt.

Mit den Beans können Sie dann entweder:
* in einer `ApplicationRunner` Bean Instanz arbeiten (d.h. dort injecten und aufrufen)
* oder Sie nutzen den von `SpringApplication.run()` zurückgegebenen Kontext und holen sich dort
Beans heraus und rufen diese auf

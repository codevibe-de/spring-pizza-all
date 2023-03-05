# EXERCISES

## Vorbereitung Eclipse

- die "Servers" View öffnen und einen Tomcat 9 Server anlegen, notfalls installieren
- die Anwendung mit Facet "Dynamic Web Module" markieren (Project > Properties)
- ebenfalls in Project Properties im Deployment Assembly die Maven Dependencies
  hinzufügen, falls diese
  fehlen (https://stackoverflow.com/questions/6210757/java-lang-classnotfoundexception-org-springframework-web-context-contextloaderl)
- die Anwendung dem Server hinzufügen (Add/Remove)
- Server starten
- im Browser http://localhost:8080/training.spring.pizza/dump öffnen

## Vorbereitung Intellij

- eine Tomcat Run Configuration anlegen
- unter Deployment mittels "+" das Projekt als "exploded" Artifakt hinzufügen
- URL http://localhost:8080/training_spring_pizza_war_exploded/dump als "After Launch URL" eintragen
- Starten

## Aufgabe

Migrieren Sie die bestehende Anwendung auf Spring Boot.

Dabei empfiehlt sich folgende Reihenfolge:

1. die `pom.xml` Datei durch eine Spring-Boot-basierte Version ersetzen (insbesondere Nutzung der Boot parent-pom)
    - wir benötigen die Starter "web" und "jdbc" 
    - außerdem noch die Bibliothek für "h2"
    - sowie (wie bisher auch) die "javax.servlet-api" Bibliothek mit scope "provided"
2. eine Spring Boot Anwendungsklasse erstellen
    - ACHTUNG: die URL der Anwendung ändert sich, wenn hierüber gestartet wird auf http://localhost:8080/dump
3. die `context.xml` durchgehen und Beans mittels Annotationen statt durch XML erzeugen
    - was könnte per XML konfiguriert bleiben?
        - die bisherige XML-Konfiguration kann mittels `@ImportResource("context.xml")` auf der Applikationsklasse
          eingelesen werden
    - wie kann von XML auf Annotationen migriert werden?
    - was kann ganz weg?
    - an Autowiring / Injections denken!
4. Servlet Klassen vereinfachen
    - `init()` kann nun weg
    - `@ServletComponentScan` an Anwendungsklasse, um die mit `@WebServlet` annotierten Servlets von Spring erkennen zu
      lassen
5. Eine DataSource Konfiguration in `application.properties` erstellen
    - siehe z.B. https://www.baeldung.com/spring-boot-h2-database
    - unsere Datenbank braucht weder User noch Password (=leer lassen)
    - der Wert der URL ändert sich nicht

Hinweise:

- Das Thema Spring-Data kommt später noch, daher behalten wir vorerst die `ProductJdbcDao` Klasse
- Insgesamt ist dies eine anspruchsvolle Übung! Helfen Sie sich gegenseitig und stellen Sie
  Fragen, sobald Sie nicht weiterkommen.
- Bei blockierenden Problemen in der Lösung nachschauen (Branch "030_migration_solution")
- ... oder in den Migrations-Notizen in der README.md ganz unten (auch im Solution-Branch) 
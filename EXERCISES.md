# EXERCISES

Migrieren Sie die bestehende Anwendung auf Spring Boot.

Dabei empfiehlt sich folgende Reihenfolge:

1. bestehende Anwendung starten (via Tomcat Deployment), nutzen und verstehen
2. die `pom.xml` Datei durch eine Spring-Boot-basierte Version ersetzen (insbesondere Nutzung der Boot parent-pom)
    - welche Starter werden benötigt?
3. die `context.xml` durchgehen und Beans mittels Annotationen statt durch XML erzeugen
    - was könnte per XML konfiguriert bleiben?
    - wie kann von XML auf Annotationen migriert werden?
    - was kann ganz weg?
4. DataSource Konfiguration in `application.properties` erstellen
   - siehe z.B. https://www.baeldung.com/spring-boot-h2-database
   - unsere Datenbank braucht weder User noch Password (=leer lassen)
   - der Wert der URL ändert sich nicht 

Hinweise:

- Das Thema Spring-Data kommt später noch, daher behalten wir vorerst die `ProductJdbcDao` Klasse
- Insgesamt ist dies eine anspruchsvolle Übung! Helfen Sie sich gegenseitig und stellen Sie
  Fragen, sobald Sie nicht weiterkommen.
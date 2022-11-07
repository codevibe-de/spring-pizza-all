# EXERCISES

Migrieren Sie die bestehende Anwendung auf Spring Boot.

Dabei empfiehlt sich folgende Reihenfolge:

- bestehende Anwendung starten (via Tomcat Deployment), nutzen und verstehen
- die `pom.xml` Datei durch eine Spring-Boot-basierte Version ersetzen (insbesondere parent-pom)
    - welche Starter werden benötigt?
- die `context.xml` durchgehen und Beans mittels Annotationen statt XML erzeugen
    - was könnte per XML konfiguriert bleiben?
    - wie kann von XML auf Annotationen migriert werden?
    - was kann ganz weg?
- Konfiguration kopieren und Property-Namen anpassen

Hinweise:

- Das Thema Spring-Data kommt später noch, daher behalten wir vorerst die `ProductJdbcDao` Klasse
- Insgesamt ist dies eine anspruchsvolle Übung! Helfen Sie sich gegenseitig und stellen Sie
  Fragen, sobald Sie nicht weiterkommen.
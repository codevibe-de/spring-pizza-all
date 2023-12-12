# Übungen zu 030 "Spring Boot Grundlagen"

Erstellen Sie eine `Dockerfile` Datei, um die gebaute Anwendung in einen ausführbaren
Docker-Container zu überführen.

Taggen Sie den Container mit `pizza-app:latest`.

Hilfreiche Links:

* https://spring.io/guides/topicals/spring-boot-docker/
* https://www.baeldung.com/dockerizing-spring-boot-application

Den Docker-Container können Sie dann wie folgt starten:

````shell
docker run --name pizza-app pizza-app:latest
````

Ggf. bekommen Sie eine Meldung, dass der Container schon existiert. Dann muss dieser vorher
gelöscht werden:

````shell
docker rm -f pizza-app
````

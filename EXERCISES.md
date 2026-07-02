# Übungen zu Kapitel "045 JDBC"

In diesem Kapitel arbeiten wir bewusst mit einer extern gestarteten Datenbank.

Dafür wurden im Projekt-Root "start-h2-server.sh" und "start-h2-server.bat" Dateien bereitgestellt, die eine H2
Datenbank starten, welche über TCP erreichbar ist.

Über den Browser kann man die Datenbank über die angegebene URL erreichen. Dazu muss diese **JDBC-URL** in den Browser
eingegeben werden: "jdbc:h2:tcp://localhost:9092/./pizzadb".

## a) Eine eigene DataSource definieren

Schauen Sie sich die Klasse `PersistenceConfig` an. Diese Klasse definiert eine `DataSource` Bean, die wir
nutzen wollen.

Entkommentieren Sie die `@Bean` Methode.

Beachten Sie, dass die `@ConditionalOnProperty` Annotation dafür sorgt, dass die Bean nur dann erstellt wird,
das entsprechende Property auf true gesetzt ist.

Setzen Sie dieses Property in der `application.properties` Datei.

Starten Sie die Anwendung.

## b) Nutzung JdbcTemplate

Wir wollen das Konstrukt der Spring `JdbcTemplate` Klasse nutzen.

Schreiben Sie die bestehende Klasse `JdbcProductRepository` um, sodass diese
Klasse nun das `JdbcTemplate` nutzt. Eine Instanz des Templates legen Sie
sich in der Repository Klasse unter Nutzung der übergebenen `DataSource` an.

Zur Refaktorierung der einzelnen Repository-Methoden bietet sich Folgendes an:

- für `save()` nutzt man `jdbcTemplate.update()`
- für `existsById()` nutzt man `jdbcTemplate.queryForObject()`
- für `findAll()` nutzt man `jdbcTemplate.query()` und erstellt (z.B. als innere Klasse)
  eine Implementierung des `RowMapper` Interfaces
- für `findById()` nutzt man `jdbcTemplate.queryForObject()` und den soeben erstellten `RowMapper`
 
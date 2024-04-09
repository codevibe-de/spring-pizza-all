# Übungen zu Kapitel "045 JDBC"

## Nutzung JdbcTemplate

Wir wollen das Konstrukt der Spring `JdbcTemplate` Klasse nutzen.

Schreiben Sie die bestehende Klasse `JdbcProductRepository` um, sodass diese
Klasse nun das `JdbcTemplate` nutzt. Eine Instanz des Templates legen Sie
sich in der Repository Klasse unter Nutzung der übergebenen `DataSource` an.

Zur Refaktorierung der einzelnen Repository-Methoden bietet sich Folgendes an:

- für `save()` nutzt man `jdbcTemplate.update()`
- für `existsById()` nutzt man `jdbcTemplate.queryForObject()`
- für `findAll()` nutzt man `jdbcTemplate.query()` und erstellt (z.B. als innere Klasse)
  eine Implementierung des `RowMapper` Interfaces
- für `findById()`  nutzt man `jdbcTemplate.queryForObject()` und den soeben erstellten `RowMapper`
 
# Übungen zu 011 - Beans Container

Migrieren Sie die Pizza Anwendung auf den "Summer Bean-Container".

Das heißt, wir wollen die Erzeugung und Verdrahtung von Beans diesem Container überlassen, sodass
wir selbst nur noch die Beans definieren und abfragen müssen.

Die Nutzung des `JdbcProductRepository` ist für unseren eigenen Bean-Container noch zu schwer, daher
bitte auf `HashMapProductRepository` umstellen. Wer mag, kann sich überlegen, was es bräuchte, um auch
die Datenbank in unserem Bean-Container nutzen zu können.
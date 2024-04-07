# Übungen zu 017 Resources

## Laden eines XML Kontext

Erstellen Sie eine Instanz der Klasse `FileSystemXmlApplicationContext`.

Diese soll die Beans aus der Datei `default-beans.xml` im Projektverzeichnis lesen.

Jedoch ist diese Datei noch fast leer - Sie dürfen die Beans hier noch definieren.

Hinweis: für XML-definierte Beans macht Spring **kein Autowiring** per Konstruktor von sich aus,
dies muss mit dem `<bean autowire="constructor">` Attribut aktiviert werden.

## Classpath Kontext

Ändern Sie den Typ des Kontexts um in einen `ClassPathXmlApplicationContext`, der
die XML-Datei aus `src/main/resources/beans/default-beans.xml` liest. Wie muss die
Ressource nun im Konstruktor benannt werden?

## Generic Kontext

Ändern Sie den Kontext nun um auf einen `GenericXmlApplicationContext` -- dieser benötigt
eine `Resource` als Parameter.

Hierfür stehen fertige Typen zur Verfügung, wie z.B.

- `ClassPathResource`
- `FileSystemResource`
- `ByteArrayResource`
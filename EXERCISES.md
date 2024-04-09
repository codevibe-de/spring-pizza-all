# Übungen zu 017 Resources

**Hinweis:** Für diese Übung wurden die Build-Skripte um die Bibliothek `org.springframework:spring-context`
erweitert -- ggf. müssen Sie das Projekt in Ihrer IDE aktualisieren lassen.

## Laden eines XML Kontext

Die Spring-Klasse `FileSystemXmlApplicationContext` ist das (professionelle) Gegenstück unseres
handgemachten `XmlBeanContainers` -- und funktioniert sehr ähnlich.

Ziel dieser Übung ist es, eine Instanz des `FileSystemXmlApplicationContext` zu erzeugen und diese
statt unseres `XmlBeanContainers` zu nutzen.

Die Beans sollen aus der Datei `default-beans.xml` im Projektverzeichnis gelesen werden, die schon
existiert, jedoch noch fast leer ist. Hier dürfen Sie die notwendigen Beans definieren.

Hinweis: für XML-definierte Beans macht Spring **kein Autowiring** per Konstruktor von sich aus,
dies muss mit dem `<bean ... autowire="constructor">` Attribut aktiviert werden.

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
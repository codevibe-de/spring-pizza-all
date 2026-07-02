# Übungen zu 017 Resources

**Hinweis:** Für diese Übung wurden die Build-Skripte um die Bibliothek `org.springframework:spring-context`
erweitert -- ggf. müssen Sie das Projekt in Ihrer IDE aktualisieren lassen.

## Laden eines XML Kontext

Die Spring-Klasse `FileSystemXmlApplicationContext` ist das (professionelle) Gegenstück unseres
handgemachten `XmlBeanContainer` -- und funktioniert sehr ähnlich.

Ziel dieser Übung ist es, nun eine Instanz des `FileSystemXmlApplicationContext` zu erzeugen und in der `PizzaApp` zu
nutzen.

Die Beans sollen aus der Datei `default-beans.xml` im Projektverzeichnis gelesen werden, die bereits
befüllt ist.

Hinweis: Für XML-definierte Beans macht Spring **kein Autowiring** per Konstruktor von sich aus,
dies muss mit dem `<bean ... autowire="constructor">` Attribut aktiviert werden -- das ist in
`default-beans.xml` bereits eingetragen.

## CSV DataLoader

In `DataLoader.java` existiert nun eine neue innere Klasse `DataLoader.Csv`. Diese liest Produkte
aus einer CSV-Datei (Format: `id;name;preis`).

Die Datei `src/main/resources/products.csv` enthält Beispieldaten.

Vorgehen:

1. Implementieren Sie das `TODO` in `DataLoader.Csv.run()`, indem Sie die Resource laden

2. Ersetzen Sie in `default-beans.xml` die Bean `DataLoader.Sample` durch `DataLoader.Csv`.
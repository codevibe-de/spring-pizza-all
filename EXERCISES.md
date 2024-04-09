# Übungen zu 013 - XML Beans Container

Migrieren Sie die Pizza Anwendung auf den "Summer **XML** Bean-Container".

Das heißt, die Bean-Definition erfolgen nun in einer separaten XML-Datei, welche folgendes Format aufweist:

````xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans>
    <bean name="the-name" class="de.example.the.Class"/>
</beans>
````

**Hinweis:** Wenn eine innere statische Klasse per Reflection geladen werden soll, so muss ein `$` Zeichen für die
Trennung von äußerem zu innerem Klassennamen genutzt werden (`DataLoader$Sample`).
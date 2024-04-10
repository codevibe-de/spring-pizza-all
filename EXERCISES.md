# Übungen zum Kapitel "075 - Web Application"

## Setup

Ihre Build-Skripte wurden bereits um folgende Dependencies erweitert:

- `spring-boot-starter-web`
- `spring-boot-starter-thymeleaf`

Ebenso ist ein neues Verzeichnis mit statischem Inhalt vorbereitet:

- `src/main/resources/static`

Was noch fehlt, ist das Verzeichnis für die Templates -- bitte anlegen:

- `src/main/resources/templates`

## ProductWebController

Schreiben Sie einen Controller namens `ProductWebController`, der ein Produkt lädt und dem Model hinzufügt.

Das Produkt können Sie über zwei Möglichkeiten identifizieren lassen:

- Query-Parameter (wie auf den Folien zu sehen)
- Path-Variable (dies ist die REST-konforme Variante) -- dies geht über die Annotation `@PathVariable` und eine
  gemappte URL wie z.B. `/products/{productId}`

Der Controller soll die View mit Namen "products/single-product" rendern lassen.

## Template

Erstellen Sie nun eine HTML-Template-Datei (`src/main/resources/templates/products/single-product.html`),
welche die Darstellung des Kunden als HTML-Response definiert.

Hier können verschiedene Attribute des Produkts dargestellt werden, die aus dem Model gezogen werden.

Denken Sie daran, über welchen Namen Sie das Produkt dem Model hinzugefügt haben -- dies ist der Einstiegspunkt zu den
Daten im Template.
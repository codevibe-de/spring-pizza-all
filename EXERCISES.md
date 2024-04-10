# Übungen zum Kapitel "075 - Web Application"

## Setup

Fügen Sie folgende Starter dem Build hinzu:

- `spring-boot-starter-web`
- `spring-boot-starter-thymeleaf`

Erzeugen Sie folgende Verzeichnisse:

- `src/main/resources/static`
- `src/main/resources/templates`

## Kunden Controller

Schreiben Sie einen Controller, der einen Kunden lädt und dem Model hinzufügt.

Der Kunde kann über einen Query-Parameter (z.B. `custId`) identifiziert werden.

Dieser Controller soll die View "customer" rendern lassen.

## Kunden Template

Schreiben Sie nun eine HTML-Template-Datei (`src/main/resources/templates/customer.html`), welche die Darstellung
des Kunden als HTML-Response übernimmt.

Hier können verschiedene Attribute des Kunden dargestellt werden, die aus dem Model gezogen werden.

Denken Sie daran, über welchen Namen Sie den Kunden dem Model hinzugefügt haben -- dies ist der Einstiegspunkt zu den
Kundendaten.
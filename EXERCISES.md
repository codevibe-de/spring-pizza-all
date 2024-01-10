# Übungen zu 016

## Entwicklung des Aspekts

Schreiben Sie einen AOP Aspekt als eine Klasse namens `ExecutionTimeAspect`.

Diese soll zwei Methoden haben:

- `beforeMethodInvocation()`
- `afterMethodInvocation(String methodName)`

In der Klasse wird bei "before" die aktuelle Systemzeit als Millisekunden festgehalten
und in "after" die Differenz zusammen mit dem Methodennamen als Information ausgegeben.

## Anwendung des Aspekts

Wenden Sie den entwickelten Aspekt auf eine der Klassen im Projekt an:

- mittels des Java `Proxy` Konzepts, wenn die Klasse ein Interface implementiert
- mittels Vererbung, wenn nicht

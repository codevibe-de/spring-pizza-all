# Übungen zu 018 Spring Expression Language

## Lieferzeit definieren

Die Klasse `OrderService` hat nun eine Setter-Methode, mit der das Property `deliveryTimeInMinutes` gesetzt werden kann.

In der Beans-XML-Datei kann entsprechend dieser Wert nun konfiguriert werden:

````xml

<bean class="pizza.order.OrderService" autowire="constructor">
    <property name="deliveryTimeInMinutes" value="..."/>
</bean>
````

Nutzen Sie die SpEL, um diesen Wert auf nicht-triviale Art zu setzen, wie z.B.

- Auslesen des Environments (Tipp: dort sind auch alle Umgebungsvariablen definiert)
- Berechnung
- Aufruf einer Bean
- Zufallszahl

Siehe auch https://docs.spring.io/spring-framework/reference/core/expressions/beandef.html
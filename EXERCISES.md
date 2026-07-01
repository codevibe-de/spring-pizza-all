# Übungen zu 018 Spring Expression Language

Hier können Sie sich für eine der beiden Übungen entscheiden - diese sind unabhängig voneinander.

## a) Lieferzeit definieren

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

## b) SpelParserApp

Nutzen Sie die Klasse `SpelParserApp` und ändern Sie den SpEL-Ausdruck, um mit den Möglichkeiten der SpEL zu
experimentieren.

Zum Beispiel können Sie auf den `ProductService` per Namen ("productService") zugreifen und dessen Produkte
abfragen:

- `"@productService.allProducts"`
- `"@productService.getProduct('S-03').name"`
- `"@productService.getProduct('S-03').price / 1.19"` (Nettopreis berechnen)

Man könnte auch auf den `ProductService` per Typ zugreifen:

- `"#applicationContext.getBean(T(pizza.product.ProductService))"` 
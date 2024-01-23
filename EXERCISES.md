# Übungen zum Kapitel "070 - Transaktionen"

Für diese Übung wurden folgende Änderungen am Code vorbereitet:

* eine `Customer` Entität hat nun einen Zähler für die Anzahl Bestellversuche (`orderCount`)
* der `CustomerService` hat eine neue Methode, um für einen Kunden die Anzahl Bestellversuche zu
  erhöhen

## a) CustomerService transaktionalisieren

Machen Sie die neue Methode
`pizza.customer.CustomerService#increaseOrderCount()` transaktional, sodass in einer
neuen Transaktion der Zähler hochgesetzt und persistiert wird.

## c) OrderService erweitern

Rufen Sie diese Methode im `CustomerService` aus `OrderService#placeOrder()` auf,  
und zwar gleich, nachdem der Customer geladen wurde (und bevor die Produkte geladen werden).

Außerdem soll die `placeOrder()` Methode auch in einer Transaktion ablaufen.

## d) Test

Schreiben Sie einen Test
`pizza.order.OrderServiceTest#placeOrder_customerOrderCountIncreasesDespiteTransactionFail()`
der einen ungültigen Bestellvorgang auslöst und dann prüft, dass dennoch
der Zähler der Customer-Entität erhöht wurde.


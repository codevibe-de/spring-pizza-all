# Übungen zu 016

## a) Entwicklung eines Tracing-Aspekts

Legen Sie ein neues Package `pizza.aop` an.

Implementieren Sie dort das Spring-Interface `MethodBeforeAdvice`, um einen Aspekt zu
implementieren, der vor jedem Aufruf einer
Methode eine Nachricht auf `System.out` schreibt (z.B. "About to execute method xyz()")

Lassen Sie in `PizzaApp.main()` mittels Springs `ProxyFactoryBean` einen AOP-Proxy von einer
bestehenden Bean-Instanz generieren (z.B. `ProductService`).

Durch Ausführung der bisherigen Geschäftslogik sollte dann der Aspekt in Ausführung kommen.

## b) Entwicklung eines Profiling-Aspekts

Implementieren Sie dann das Interface `org.aopalliance.intercept.MethodInterceptor`, um **vor und
nach** einer Methode Code ausführen zu können (Umwicklung eines Aufrufs).

Führen Sie darin eine vorher/nachher Zeitmessung durch und geben dann auf der Konsole aus,
wie lange die umwickelte Methode für die Ausführung gebraucht hat.



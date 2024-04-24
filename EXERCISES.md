# Übungen zu 016

## a) Entwicklung eines Tracing-Aspekts

Implementieren Sie das Interface `MethodBeforeAdvice`, um einen Aspekt zu implementieren, der vor jedem Aufruf einer
Methode eine Nachricht auf `System.out` schreibt (z.B. "About to execute method xyz()")

Lassen Sie Spring mittels der `ProxyFactoryBean` einen AOP-Proxy von einer Instanz generieren (z.B. `ProductService`)
und rufen Sie dann diese Instanz auf. Wird der AOP-Aspekt ausgeführt?

## b) Entwicklung eines Profiling-Aspekts

Implementieren Sie dann das Interface `MethodInterceptor`, um **vor und nach** einer Methode Code ausführen zu können
(Umwicklung eines Aufrufs). Führen Sie darin eine vorher/nachher Zeitmessung durch und geben dann auf der Konsole aus,
wie lange die umwickelte Methode für die Ausführung gebraucht hat.



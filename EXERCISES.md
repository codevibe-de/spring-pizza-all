# Übungen zu Kapitel "100 - Profile, Logging und Monitoring"

## a) "Nur Customer” Profil

Markieren Sie alle relevanten Beans mit `@Profile` Annotationen, sodass bei Aufruf
der Anwendung mit dem Profil "customer" nur die Funktionalität des Customer-Moduls
zur Verfügung steht.

Gerne können Sie auch die Übung so weit treiben, dass die folgenden Profile zur Auswahl
stehen und die Anwendung bei jedem der Profile lauffähig ist:
* "product"
* "customer"
* "order"
* kein Profil gesetzt

## b) “Dev” Profil

Legen Sie eine Konfigurationsdatei für das Profil "dev" an, bei dem vermehrt
Logging Ausgaben generiert werden, z.B. Logging-Level aller "pizza" Klassen auf DEBUG.

## c) System.out durch Logging

Ersetzen Sie Ausgaben in der Anwendung, die bisher per System.out.println() erfolgt sind,
durch Logging Ausgaben.

## d) Monitoring aktivieren

Aktivieren Sie Monitoring für die Anwendung, sodass folgende Endpunkte im Browser abrufbar sind:
* health
* info
* env

## e) Git Build Informationen aufnehmen

Fügen Sie den folgenden Abschnitt in die `pom.xml` ein, um den Info Endpoint um Informationen über
den Build-Prozess und den letzten Git-Commit zu erweitern:

````xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                    <id>build-info</id>
                    <goals>
                        <goal>build-info</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <groupId>pl.project13.maven</groupId>
            <artifactId>git-commit-id-plugin</artifactId>
        </plugin>
    </plugins>
</build>
````

Wenn Sie nun die Anwendung über **Maven** bauen und starten, dann werden diese Informationen über
den Info-Endpoint angezeigt.

## f) Neuen Health-Indicator entwickeln

Schreiben Sie einen neuen `HealthIndicator` namens `HasProductsHealthIndicator`, der prüft, ob
Produkte zur Bestellung im System vorhanden sind. Sind keine Produkte vorhanden, so ist das 
System im Status "DOWN", ansonsten "UP".

Dieser Indicator wird dann in die gesamte Health-Betrachtung des Systems einfließen.


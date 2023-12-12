FROM openjdk:17

# maven:
COPY target/pizza-app-*.jar pizza-app.jar
# gradle:
COPY build/libs/pizza-app-*.jar pizza-app.jar

ENTRYPOINT ["java","-jar","/pizza-app.jar"]
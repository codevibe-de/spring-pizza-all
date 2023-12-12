FROM openjdk:17
COPY build/libs/pizza-app-*.jar pizza-app.jar
ENTRYPOINT ["java","-jar","/pizza-app.jar"]
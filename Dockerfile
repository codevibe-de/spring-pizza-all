# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# docker build -t pizza:latest .
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

FROM eclipse-temurin:17

COPY target/pizza-app-*.jar pizza-app.jar

ENTRYPOINT ["java","-jar","/pizza-app.jar"]
FROM eclipse-temurin:17-jdk-alpine

# Creacion de un punto de montaje
VOLUME /tmp

COPY target/clase-42-2-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOIT ["java", "-jar", "/app.jar"]
EXPOSE 8080

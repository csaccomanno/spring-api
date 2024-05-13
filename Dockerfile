FROM eclipse-temurin:17-jdk-alphine
VOLUME /tmp
COPY target/clase-42-2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOIT ["java", "-jar", "/app.jar"]
EXPOSE 8080

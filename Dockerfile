FROM openjdk:17-jdk-slim

COPY target/tech-challenge-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
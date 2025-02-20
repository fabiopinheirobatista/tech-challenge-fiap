FROM openjdk:17-jdk-slim
COPY pom.xml /build/

COPY target/TechChallenge-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /build/

RUN mvn package

FROM openjdk:17-jdk-slim
ENV TZ=America/Sao_Paulo

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
FROM maven:3.8.7-openjdk-18-slim AS maven_build
COPY pom.xml /build/

COPY src /build/src/
WORKDIR /build/

RUN mvn package

FROM openjdk:17.0.1-jdk-slim
ENV TZ=America/Sao_Paulo

WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar

EXPOSE 9093
ENTRYPOINT ["java", "--add-opens", "java.base/java.io=ALL-UNNAMED", "-jar", "/app/app.jar"]
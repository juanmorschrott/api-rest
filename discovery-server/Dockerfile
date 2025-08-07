FROM maven:3.9.11-eclipse-temurin-21 AS builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package

FROM eclipse-temurin:21-jre-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/discovery-server.jar
WORKDIR /app
CMD ["java", "-jar", "-Dspring.profiles.active=production", "discovery-server.jar"]
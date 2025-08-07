FROM maven:3.9.11-amazoncorretto-21 as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/discovery-server.jar
WORKDIR /app
CMD ["java", "-jar", "-Dspring.profiles.active=production", "discovery-server.jar"]
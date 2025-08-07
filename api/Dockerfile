FROM maven:3.9.11-amazoncorretto-21 as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/api.jar
WORKDIR /app
ENV SPRING_DATASOURCE_URL jdbc:mysql://db:3306/api?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
CMD ["java", "-jar", "-Dspring.profiles.active=production", "api.jar"]

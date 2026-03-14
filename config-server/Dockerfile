FROM maven:3.9.6-eclipse-temurin-21-alpine as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.2_13-jre-alpine
RUN apk add --no-cache curl
COPY --from=builder /usr/src/app/target/*.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]

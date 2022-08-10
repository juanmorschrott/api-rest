FROM maven as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/gateway.jar
WORKDIR /app
CMD ["java", "-jar", "-Dspring.profiles.active=production", "gateway.jar"]
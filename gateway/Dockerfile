FROM maven:3.9.11-amazoncorretto-25 as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM amazoncorretto:25-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/gateway.jar
WORKDIR /app
CMD ["java", "-jar", "gateway.jar"]
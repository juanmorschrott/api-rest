FROM maven as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=builder /usr/src/app/target/*.jar /app/api.jar
WORKDIR /app
ENV SPRING_DATASOURCE_URL jdbc:mysql://db:3306/api?autoReconnect=true&useSSL=false
CMD ["java", "-jar", "-Dspring.profiles.active=production", "api.jar"]

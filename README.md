# Api-rest

API Rest para pruebas y Single Page Application.

## Dependencias

 - Java openjdk version "1.8.0_222"
 - Node v10.16.3
    - @angular/cli

## Componentes

### Backend

Aplicación WEB CRUD Spring-boot con base de datos H2.

Ejecutar: 

```
./mvnw clean install
./mvnw spring-boot:run
```

BASE-URL:   http://localhost:8080/api/v1/hotels

|OPERATION|METHOD|URI|
|---|---|---|
|CREATE|GET|http://localhost:8080/api/v1/hotels|
|READ|GET|http://localhost:8080/api/v1/hotels/{id}|
|UPDATE|PUT|http://localhost:8080/api/v1/hotels|
|DELETE|DELETE|http://localhost:8080/api/v1/hotels/{id}|

### Frontend

Aplicación Angular

Ejecutar: 

```
npm install
ng serve
```

URL:   http://localhost:4200

# Api-rest

Simple API Rest y Single Page Application.

## Dependencias

 - Java openjdk version "17"
 - Lombok plugin installed in your IDE
 - Node v16.16.3
    - @angular/cli
 - Docker & docker-compose

## Componentes

### Backend

Aplicación WEB CRUD Spring-boot con base de datos H2.

Ejecutar: 

```bash
$ cd ./api-rest
$ docker-compose up
```

Probar aplicación:

URL:   http://localhost

BASE-URL:   http://localhost:8080/api/v1/hotels

|OPERATION|METHOD|URI|
|---|---|---|
|CREATE|GET|http://localhost:8080/api/v1/hotels|
|READ|GET|http://localhost:8080/api/v1/hotels/{id}|
|UPDATE|PUT|http://localhost:8080/api/v1/hotels|
|DELETE|DELETE|http://localhost:8080/api/v1/hotels/{id}|

API Documentation:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

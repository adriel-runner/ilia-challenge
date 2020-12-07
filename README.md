# Ilía Challenge

### Description
This project was created as a step of a technical evaluation.

### Deployed App on Heroku
https://adriel-ilia-challenge.herokuapp.com/swagger-ui.html

### Login OAuth2
It's necessary authenticate with a gmail account to access the app endpoint.

##### Build with
Spring Boot
OAuth2
Swagger
A Memory Java Database (HSQLDB)
Flyway


### How to start the application
#### Prerequisites
- [X] Java 11+
- [X] Git
- [X] Docker

#### Start application
1 - Clone the project
```shell
git clone https://github.com/adriel-runner/desafio-pismo.git
``` 

2 - Build
```shell
./mvnw package
```

3 - Run
```shell
./mvnw spring-boot:run
```

4 - Open
```shell
http://localhost:8080/swagger-ui/
```

#### Start application with Docker
From your application’s root directory

1 - Build docker image
```shell
./mvnw docker:build
```

2 - Run docker
```shell
docker run --publish 8080:8080 --detach --name <CONTAINER_NAME> <DOCKER_IMAGE>:<DOCKER_TAG>
```

3 - Open
```shell
http://localhost:8080/swagger-ui/
```

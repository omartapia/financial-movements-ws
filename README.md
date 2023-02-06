# Spring Boot Movimientos api

This application was developed to demonstrate Spring Boot with MySQL with simple API.

Technologies Used

- Spring Boot 2.4.1
- Spring Data JPA
- Lombok
- MySQL

How to Run this application

First generate a build

```shell
$ ./gradlew clean build
```

next, run with this command:

```shell
$ docker-compose build
$ docker-compose up
```

To stop the api

```shell
$ docker-compose down
```

if you want remove all images

```shell
$ docker-compose down -rmi all
```

### Created by

- Omar Santiago Tapia Hidalgo (omar.tapia.h@gmail.com)

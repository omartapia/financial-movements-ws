FROM openjdk:8-jdk-alpine
LABEL maintainer="omar.tapia.h@gmail.com"
VOLUME /main-app
ADD build/libs/financial-movements-ws-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]
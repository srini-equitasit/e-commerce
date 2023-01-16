#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar notification-service.jar
ENTRYPOINT ["java","-jar","/notification-service.jar"]

EXPOSE 8081
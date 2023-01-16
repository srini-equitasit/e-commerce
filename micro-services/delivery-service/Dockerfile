#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar delivery-service.jar
ENTRYPOINT ["java","-jar","/delivery-service.jar"]

EXPOSE 8081